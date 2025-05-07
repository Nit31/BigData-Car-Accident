"""
Module for training and evaluating Logistic Regression and
Decision Tree using PySpark.

This module contains functions for:
- Preprocessing data
- Training models with hyperparameter tuning
- Saving and loading models
- Evaluating model performance
"""
import math
import os

import pyspark.sql.functions as F
from pyspark.ml import Pipeline, Transformer
from pyspark.ml.classification import DecisionTreeClassifier, LogisticRegression
from pyspark.ml.evaluation import MulticlassClassificationEvaluator
from pyspark.ml.feature import (
    Imputer,
    OneHotEncoder,
    StandardScaler,
    StringIndexer,
    VectorAssembler,
)
from pyspark.ml.functions import vector_to_array
from pyspark.ml.param.shared import HasInputCols, HasOutputCols, Param, Params, TypeConverters
from pyspark.ml.tuning import CrossValidator, ParamGridBuilder
from pyspark.ml.util import DefaultParamsReadable, DefaultParamsWritable
from pyspark.sql import DataFrame, SparkSession, Window
from pyspark.sql.functions import (
    cos,
    dayofweek,
    hour,
    lit,
    minute,
    month,
    radians,
    row_number,
    second,
    sin,
    udf,
    when,
    year,
)
from pyspark.sql.types import ArrayType, DoubleType, StringType, StructField, StructType

# Setup Hive conection
TEAM = 13
WAREHOUSE = "project/hive/warehouse"
spark = (
    SparkSession.builder.appName("{} - spark ML".format(TEAM))
    .master("yarn")
    .config("hive.metastore.uris", "thrift://hadoop-02.uni.innopolis.ru:9883")
    .config("spark.sql.warehouse.dir", WAREHOUSE)
    .config("spark.sql.avro.compression.codec", "snappy")
    .enableHiveSupport()
    .getOrCreate()
)


def get_data() -> DataFrame:
    """
    Function for obtain our data from Hive
    """
    global spark
    data_df = spark.sql(
        "SELECT * FROM team13_projectdb.accidents_partitioned_bucketed")
    return data_df


def preprocess_numerical_features(data_df: DataFrame, numerical_cols: list):
    """
    Function for imputing numerical features based on state mean, scaling it and cleanup
    """
    global spark
    window_state = Window.partitionBy("State")

    for column in numerical_cols:
        data_df = data_df.withColumn(
            column, F.coalesce(F.col(column), F.mean(
                F.col(column)).over(window_state))
        )

    assembler = VectorAssembler(
        inputCols=numerical_cols, outputCol="num_features_raw")

    scaler = StandardScaler(
        inputCol=assembler.getOutputCol(),
        outputCol="scaled_num_features",
        withMean=True,
        withStd=True,
    )

    pipeline = Pipeline(stages=[assembler, scaler])
    pipeline_model = pipeline.fit(data_df)
    processed_df = pipeline_model.transform(data_df)

    array_df = processed_df.withColumn(
        "scaled_array", vector_to_array("scaled_num_features")
    )

    for i, column in enumerate(numerical_cols):
        array_df = array_df.withColumn(
            f"scaled_{column}", F.col("scaled_array")[i].cast("double")
        )

    cols_to_drop = numerical_cols + [
        "num_features_raw",
        "scaled_num_features",
        "scaled_array",
    ]
    final_df = array_df.drop(*cols_to_drop)

    for column in numerical_cols:
        final_df = final_df.withColumnRenamed(f"scaled_{column}", column)

    return final_df


class DropNullTimestampTransformer(
    Transformer, DefaultParamsReadable, DefaultParamsWritable
):
    """
    Transformer for dropping raws with null values in weather_timestamp - feature
    representing time of last weather conditions estimation
    """
    def __init__(self, colsToDropNulls=None):
        super().__init__()
        self.colsToDropNulls = colsToDropNulls

    def _transform(self, dataset):
        if self.colsToDropNulls:
            return dataset.na.drop(subset=self.colsToDropNulls)

        return dataset


class ExtractTimeFeaturesTransformer(
    Transformer, DefaultParamsReadable, DefaultParamsWritable
):
    """
    Transform timestamp format to separated features
    """
    def __init__(self, timestampCols=None):
        super().__init__()
        self.timestampCols = timestampCols

    def _transform(self, dataset):
        for ts_col in self.timestampCols:
            dataset = (
                dataset.withColumn(f"{ts_col}_year", year(
                    ts_col).cast(DoubleType()))
                .withColumn(f"{ts_col}_month", month(ts_col).cast(DoubleType()))
                .withColumn(f"{ts_col}_weekday", dayofweek(ts_col).cast(DoubleType()))
                .withColumn(f"{ts_col}_hour", hour(ts_col).cast(DoubleType()))
                .withColumn(f"{ts_col}_minute", minute(ts_col).cast(DoubleType()))
                .withColumn(f"{ts_col}_second", second(ts_col).cast(DoubleType()))
            )
        return dataset


class CyclicalEncodingTransformer(
    Transformer, DefaultParamsReadable, DefaultParamsWritable
):
    """
    Transformer with Cyclical encoder for date and time features
    """
    def __init__(self, timestampCols=None):
        super().__init__()
        self.timestampCols = timestampCols

    def _transform(self, dataset):
        def cyclical_encode(col_name, period):
            radians = (2 * lit(3.141592653589793) * col_name) / period
            return (cos(radians).cast(DoubleType()), sin(radians).cast(DoubleType()))

        for ts_col in self.timestampCols:
            month_sin, month_cos = cyclical_encode(dataset[f"{ts_col}_month"], 12)
            hour_sin, hour_cos = cyclical_encode(dataset[f"{ts_col}_hour"], 24)
            minute_sin, minute_cos = cyclical_encode(
                dataset[f"{ts_col}_minute"], 60)
            second_sin, second_cos = cyclical_encode(
                dataset[f"{ts_col}_second"], 60)

            dataset = (
                dataset.withColumn(f"{ts_col}_month_sin", month_sin)
                .withColumn(f"{ts_col}_month_cos", month_cos)
                .withColumn(f"{ts_col}_hour_sin", hour_sin)
                .withColumn(f"{ts_col}_hour_cos", hour_cos)
                .withColumn(f"{ts_col}_minute_sin", minute_sin)
                .withColumn(f"{ts_col}_minute_cos", minute_cos)
                .withColumn(f"{ts_col}_second_sin", second_sin)
                .withColumn(f"{ts_col}_second_cos", second_cos)
            )
        return dataset


def encode_timestamp_features(data_df: DataFrame) -> DataFrame:
    """
    Agregate UDTs, imputing, scaling, and vectors assembing to pipeline and
    perform timestamp encoding. Then clean up transformed columns.
    """
    timestamp_cols = ["start_time", "end_time", "weather_timestamp"]

    drop_null_transformer = DropNullTimestampTransformer(
        colsToDropNulls=["weather_timestamp"]
    )
    extract_transformer = ExtractTimeFeaturesTransformer(
        timestampCols=timestamp_cols)
    cyclical_transformer = CyclicalEncodingTransformer(
        timestampCols=timestamp_cols)

    columns_to_scale = []
    for ts_col in timestamp_cols:
        columns_to_scale.extend([f"{ts_col}_year", f"{ts_col}_weekday"])

    imputer = Imputer(
        inputCols=columns_to_scale,
        outputCols=[f"{col}_imputed" for col in columns_to_scale],
        strategy="mean",
    )
    assembler = VectorAssembler(
        inputCols=imputer.getOutputCols(), outputCol="features_to_scale"
    )
    scaler = StandardScaler(
        inputCol="features_to_scale",
        outputCol="scaled_features",
        withMean=True,
        withStd=True,
    )

    pipeline = Pipeline(
        stages=[
            drop_null_transformer,
            extract_transformer,
            cyclical_transformer,
            imputer,
            assembler,
            scaler,
        ]
    )

    scaled_df = pipeline.fit(data_df).transform(data_df)

    vector_to_array = udf(lambda v: v.toArray().tolist(),
                          ArrayType(DoubleType()))
    scaled_df = scaled_df.withColumn(
        "scaled_array", vector_to_array("scaled_features"))

    for i, col in enumerate(columns_to_scale):
        scaled_df = scaled_df.withColumn(
            f"{col}_scaled", scaled_df["scaled_array"][i].cast(DoubleType())
        )

    cols_to_drop = (
        timestamp_cols
        + [f"{ts_col}_month" for ts_col in timestamp_cols]
        + [f"{ts_col}_hour" for ts_col in timestamp_cols]
        + [f"{ts_col}_minute" for ts_col in timestamp_cols]
        + [f"{ts_col}_second" for ts_col in timestamp_cols]
        + columns_to_scale
        + imputer.getOutputCols()
        + ["features_to_scale", "scaled_features", "scaled_array"]
    )

    scaled_df = scaled_df.drop(*cols_to_drop)

    for col in columns_to_scale:
        scaled_df = scaled_df.withColumnRenamed(f"{col}_scaled", col)

    return scaled_df


def impute_categorical_features(data_df: DataFrame, categorical_features: list) -> DataFrame:
    """
    Imputing categorical features using mode value in the state. Handle city imputations separetly
    """
    df_imputed = data_df
    features_with_nulls = []

    for feature in categorical_features:
        missing_count = data_df.filter(data_df[feature].isNull()).count()
        if missing_count > 0:
            features_with_nulls.append(feature)
            print(
                f"Feature {feature} has {missing_count} missing values - will impute with mode"
            )

    if "City" in features_with_nulls:
        print("\nSpecial handling for City: Using mode city within each State")

        state_city_modes = (
            data_df.filter(F.col("City").isNotNull())
            .groupBy("State", "City")
            .count()
            .orderBy("State", F.col("count").desc())
        )

        window = Window.partitionBy("State").orderBy(F.col("count").desc())
        city_modes_by_state = (
            state_city_modes.withColumn("row", row_number().over(window))
            .filter(F.col("row") == 1)
            .select("State", "City")
        )

        global_city_mode = (
            data_df.filter(F.col("City").isNotNull())
            .groupBy("City")
            .count()
            .orderBy(F.col("count").desc())
            .limit(1)
            .collect()[0]["City"]
        )

        print(f"Global city mode (fallback): {global_city_mode}")

        state_to_city_dict = {
            row["State"]: row["City"] for row in city_modes_by_state.collect()
        }

        def get_mode_city_by_state(state):
            return state_to_city_dict.get(state, global_city_mode)

        mode_city_udf = udf(get_mode_city_by_state, StringType())

        df_imputed = df_imputed.withColumn(
            "City",
            when(F.col("City").isNull(), mode_city_udf(F.col("State"))).otherwise(
                F.col("City")
            ),
        )

        features_with_nulls.remove("City")

    for feature in features_with_nulls:
        mode_value = (
            data_df.filter(F.col(feature).isNotNull())
            .groupBy(feature)
            .count()
            .orderBy("count", ascending=False)
            .limit(1)
            .collect()[0][feature]
        )

        print(f"Imputing {feature} with mode value: {mode_value}")

        df_imputed = df_imputed.withColumn(
            feature,
            when(F.col(feature).isNull(), lit(
                mode_value)).otherwise(F.col(feature)),
        )

    data_df = df_imputed

    return data_df


def encode_categorical_features(data_df: DataFrame) -> DataFrame:
    """
    Agregate string indexing and one-hot encoding to pipeline for encoding
    categorical features.
    """
    county_freq = data_df.groupBy("County").count()
    city_freq = data_df.groupBy("City").count()

    total_count = data_df.count()
    county_freq = county_freq.withColumn(
        "county_frequency", F.col("count") / total_count
    )
    city_freq = city_freq.withColumn(
        "city_frequency", F.col("count") / total_count)

    data_df = data_df.join(
        county_freq.select("County", "county_frequency"), on="County", how="left"
    )
    data_df = data_df.join(city_freq.select("City", "city_frequency"),
                 on="City", how="left")

    side_indexer = StringIndexer(
        inputCol="side", outputCol="SideIndex", handleInvalid="keep"
    )
    side_encoder = OneHotEncoder(inputCol="SideIndex", outputCol="side_vec")

    state_indexer = StringIndexer(
        inputCol="state", outputCol="StateIndex", handleInvalid="keep"
    )
    state_encoder = OneHotEncoder(inputCol="StateIndex", outputCol="state_vec")

    weather_indexer = StringIndexer(
        inputCol="Weather_Condition", outputCol="WeatherIndex", handleInvalid="keep"
    )
    weather_encoder = OneHotEncoder(
        inputCol="WeatherIndex", outputCol="weather_vec")

    pipeline = Pipeline(
        stages=[
            side_indexer,
            side_encoder,
            state_indexer,
            state_encoder,
            weather_indexer,
            weather_encoder,
        ]
    )

    model = pipeline.fit(data_df)
    data_df = model.transform(data_df)

    return data_df


class GeoToECEFTransformer(
    Transformer,
    HasInputCols,
    HasOutputCols,
    DefaultParamsReadable,
    DefaultParamsWritable,
):
    """
    Transformer for encoding of the geographical features to ECEF format
    """
    inputInRadians = Param(
        Params._dummy(),
        "inputInRadians",
        "Whether input coordinates are in radians (True) or degrees (False)",
        typeConverter=TypeConverters.toBoolean,
    )

    def __init__(self, inputCols=None, outputCols=None, inputInRadians=False):
        super().__init__()
        self._setDefault(inputCols=None, outputCols=None, inputInRadians=False)

        if inputCols and outputCols:
            self.setInputCols(inputCols)
            self.setOutputCols(outputCols)

        self.setInputInRadians(inputInRadians)

    def setInputCols(self, value):
        """
        Sets the list of input column names.
        """
        return self._set(inputCols=value)

    def setOutputCols(self, value):
        """
        Sets the name of the output column.
        """
        return self._set(outputCols=value)

    def setInputInRadians(self, value):
        """
        Sets whether input coordinates are in radians (True) or degrees (False).
        """
        return self._set(inputInRadians=value)

    def getInputInRadians(self):
        """
        Gets whether input coordinates are in radians (True) or degrees (False).
        """
        return self.getOrDefault(self.inputInRadians)

    def _transform(self, dataset):
        """
        Transform the dataset by converting geographic coordinates to ECEF coordinates.
        """
        # Constants from the Java class
        a = 6378137.0  # WGS-84 semi-major axis
        e2 = 6.6943799901377997e-3  # WGS-84 first eccentricity squared

        lat_col, lon_col, alt_col = self.getInputCols()

        x_col, y_col, z_col = self.getOutputCols()

        def geo_to_ecef(lat, lon, alt):
            """
            Convert geographic coordinates to ECEF.
            Input: latitude and longitude in radians, altitude in meters
            Output: tuple of (x, y, z) in meters
            """
            if lat is None or lon is None or alt is None:
                return (None, None, None)

            n = a / math.sqrt(1 - e2 * math.sin(lat) * math.sin(lat))

            x = (n + alt) * math.cos(lat) * math.cos(lon)
            y = (n + alt) * math.cos(lat) * math.sin(lon)
            z = (n * (1 - e2) + alt) * math.sin(lat)

            return (x, y, z)

        # Register UDF with appropriate return type
        ecef_schema = StructType(
            [
                StructField("x", DoubleType(), True),
                StructField("y", DoubleType(), True),
                StructField("z", DoubleType(), True),
            ]
        )
        ecef_udf = udf(geo_to_ecef, ecef_schema)

        # If input is in degrees, convert to radians first
        if not self.getInputInRadians():
            lat_expr = radians(F.col(lat_col))
            lon_expr = radians(F.col(lon_col))
        else:
            lat_expr = F.col(lat_col)
            lon_expr = F.col(lon_col)

        result = dataset.withColumn(
            "ecef_coords", ecef_udf(lat_expr, lon_expr, F.col(alt_col))
        )
        result = result.withColumn(x_col, result.ecef_coords.x)
        result = result.withColumn(y_col, result.ecef_coords.y)
        result = result.withColumn(z_col, result.ecef_coords.z)

        return result.drop("ecef_coords")

    def copy(self, extra=None):
        """
        Creates a copy of this instance.
        """
        if extra is None:
            extra = {}
        return super(GeoToECEFTransformer, self).copy(extra)


def encode_spatial_features(df: DataFrame) -> DataFrame:
    """
    Function for geospacial features encoding
    """
    if "Altitude" not in df.columns:
        df = df.withColumn("Altitude", lit(0.0))

    geo_to_ecef_start = GeoToECEFTransformer(
        inputCols=["Start_Lat", "Start_Lng", "Altitude"],
        outputCols=["ecef_start_x", "ecef_start_y", "ecef_start_z"],
        inputInRadians=False,
    )
    geo_to_ecef_end = GeoToECEFTransformer(
        inputCols=["End_Lat", "End_Lng", "Altitude"],
        outputCols=["ecef_end_x", "ecef_end_y", "ecef_end_z"],
        inputInRadians=False,
    )
    pipeline = Pipeline(stages=[geo_to_ecef_start, geo_to_ecef_end])
    df = pipeline.fit(df).transform(df)
    return df


def cleanup_data(df: DataFrame) -> DataFrame:
    """
    Additional function for redundant columns removement anfter preprocessing
    """
    df = df.drop(
        "City",
        "county",
        "start_lat",
        "start_lng",
        "end_lat",
        "end_lng",
        "side",
        "Weather_Condition",
        "state",
        "sunrise_sunset",
        "SideIndex",
        "WeatherIndex",
        "StateIndex",
        "Altitude",
    )
    return df


def run(command):
    """
    Function for running hdsf comands
    """
    return os.popen(command).read()


def prepare_and_save_data(
    encoded_df: DataFrame,
    test_size: float = 0.3,
    label_col: str = "severity",
    feature_exclude_cols: list = ["id", "severity"],
    hdfs_train_path: str = "/user/team13/project/data/train",
    hdfs_test_path: str = "/user/team13/project/data/test",
    local_train_path: str = "/home/team13/project/BigData-Car-Accident/data/train.json",
    local_test_path: str = "/home/team13/project/BigData-Car-Accident/data/test.json",
):
    """
    Function for performing data split of the encoded data and save it to hdfs
    """
    feature_cols = [
        col for col in encoded_df.columns if col not in feature_exclude_cols
    ]
    train_df, test_df = encoded_df.randomSplit(
        [1 - test_size, test_size], seed=42)

    assembler = VectorAssembler(
        inputCols=feature_cols, outputCol="features", handleInvalid="skip"
    )

    train_assembled = assembler.transform(train_df).select(
        "features", F.col(label_col).alias("label")
    )
    test_assembled = assembler.transform(test_df).select(
        "features", F.col(label_col).alias("label")
    )

    train_assembled.select("features", "label").coalesce(1).write.mode(
        "overwrite"
    ).format("json").save(hdfs_train_path)

    test_assembled.select("features", "label").coalesce(1).write.mode(
        "overwrite"
    ).format("json").save(hdfs_test_path)

    run(f"hdfs dfs -cat {hdfs_train_path}/*.json > {local_train_path}")
    run(f"hdfs dfs -cat {hdfs_test_path}/*.json > {local_test_path}")

    print(f"Train data saved to: {local_train_path}")
    print(f"Test data saved to: {local_test_path}")

    return train_assembled, test_assembled


def save_predictions(predictions: DataFrame, hdfs_path: str, local_path: str):
    """
    Function for saving predictions of trained model to hdfs
    """
    selected_df = predictions.select("label", "prediction").coalesce(1)

    selected_df.write.mode("overwrite").format("csv").option("header", "true").save(
        hdfs_path
    )

    run(f"hdfs dfs -cat {hdfs_path}/*.csv > {local_path}")

    print(f"Predictions saved to HDFS at: {hdfs_path}")
    print(f"Local CSV file saved to: {local_path}")

    return local_path


def save_metrics_to_csv(metrics_list, local_file_path: str, hdfs_file_path: str):
    """
    Function for saving performance metrics of trained model to hdfs
    """
    global spark
    metrics_df = spark.createDataFrame(metrics_list)
    metrics_df.coalesce(1).write.mode("overwrite").format("csv").option(
        "header", "true"
    ).save(hdfs_file_path)

    run(f"hdfs dfs -cat {hdfs_file_path}/*.csv > {local_file_path}")

    print(f"Metrics saved successfully to: {local_file_path}")
    return local_file_path



def train_lr_with_grid_search(
    train_df: DataFrame,
    test_df: DataFrame,
    output_model_path: str = "/user/team13/project/models/model1",
):
    """
    Function for fine-tuning parameters of the logistic regression using
    grid search and cross validation, saving model to hdfs and obtain
    it test predictions and best model setting from GS
    """
    lr = LogisticRegression(
        featuresCol="features", labelCol="label", family="multinomial"
    )

    param_grid = (
        ParamGridBuilder()
        .addGrid(lr.regParam, [0.01, 0.2])
        .addGrid(lr.elasticNetParam, [0.0, 0.8])
        .build()
    )

    evaluator = MulticlassClassificationEvaluator(metricName="f1")

    crossval = CrossValidator(
        estimator=lr,
        estimatorParamMaps=param_grid,
        evaluator=evaluator,
        numFolds=3,
        seed=42,
    )

    print("Training cross validator...")
    cv_model = crossval.fit(train_df)

    best_model = cv_model.bestModel

    best_model.write().overwrite().save(output_model_path)

    predictions = best_model.transform(test_df)

    accuracy_evaluator = MulticlassClassificationEvaluator(
        metricName="accuracy")
    test_acc = accuracy_evaluator.evaluate(predictions)
    print(f"Test Accuracy: {test_acc:.4f}")

    test_f1 = evaluator.evaluate(predictions)
    print(f"Test F1 Score: {test_f1:.4f}")
    model_data = {
        "f1": test_f1,
        "acc": test_acc,
    }

    for i in [1, 2, 3, 4]:
        try:
            print(f"Severity: {i}")
            evaluator_pbl = MulticlassClassificationEvaluator(
                metricName="precisionByLabel", metricLabel=i
            )
            test_pbl = evaluator_pbl.evaluate(predictions)
            print(f"Test PBL: {test_pbl}")

            evaluator_rbl = MulticlassClassificationEvaluator(
                metricName="recallByLabel", metricLabel=i
            )
            test_rbl = evaluator_rbl.evaluate(predictions)
            print(f"Test RBL: {test_pbl}")

            model_data[f"recall_{i}"] = test_rbl
            model_data[f"precision_{i}"] = test_pbl
        except Exception:
            model_data[f"recall_{i}"] = -1
            model_data[f"precision_{i}"] = -1

    model_data["name"] = (
        f"lr: regParam:{best_model.getRegParam()}, elasticNetParam: {best_model.getElasticNetParam()}"
    )

    return predictions, model_data


def train_dt_with_grid_search(
    train_df: DataFrame,
    test_df: DataFrame,
    output_model_path: str = "/user/team13/project/models/model2",
):
    """
    Function for fine-tuning parameters of the logistic regression using
    grid search and cross validation, saving model to hdfs and obtain
    it test predictions and best model setting from GS
    """
    dt = DecisionTreeClassifier(
        featuresCol="features", labelCol="label", seed=42)

    param_grid = (
        ParamGridBuilder()
        .addGrid(dt.maxDepth, [3, 5])
        .addGrid(dt.impurity, ["gini", "entropy"])
        .build()
    )

    evaluator = MulticlassClassificationEvaluator(metricName="f1")

    crossval = CrossValidator(
        estimator=dt,
        estimatorParamMaps=param_grid,
        evaluator=evaluator,
        numFolds=3,
        seed=42,
    )

    print("Training cross validator...")
    cv_model = crossval.fit(train_df)

    best_model = cv_model.bestModel

    best_model.write().overwrite().save(output_model_path)

    predictions = best_model.transform(test_df)

    accuracy_evaluator = MulticlassClassificationEvaluator(
        metricName="accuracy")
    test_acc = accuracy_evaluator.evaluate(predictions)
    print(f"Test Accuracy: {test_acc:.4f}")

    test_f1 = evaluator.evaluate(predictions)
    print(f"Test F1 Score: {test_f1:.4f}")

    model_data = {
        "f1": test_f1,
        "acc": test_acc,
    }

    for i in [1, 2, 3, 4]:
        try:
            print(f"Severity: {i}")
            evaluator_pbl = MulticlassClassificationEvaluator(
                metricName="precisionByLabel", metricLabel=i
            )
            test_pbl = evaluator_pbl.evaluate(predictions)
            print(f"Test PBL: {test_pbl}")

            evaluator_rbl = MulticlassClassificationEvaluator(
                metricName="recallByLabel", metricLabel=i
            )
            test_rbl = evaluator_rbl.evaluate(predictions)
            print(f"Test RBL: {test_pbl}")

            model_data[f"recall_{i}"] = test_rbl
            model_data[f"precision_{i}"] = test_pbl
        except Exception:
            model_data[f"recall_{i}"] = -1
            model_data[f"precision_{i}"] = -1

    model_data["name"] = (
        f"dt: maxDepth:{best_model.getMaxDepth()}, impurity: {best_model.getImpurity()}"
    )

    return predictions, model_data


def main():
    """
    Executing data loading, preparation, saving to hdfs
    Also training and fine-tining the models and save them and their
    setting, metrics, and predictions to hdfs
    """
    # Load data
    print("\n\n\nLoading data...")
    df = get_data()

    # Processing
    print("\n\n\nProcessing data...")
    numerical_features = [
        "distance_mi",
        "temperature_f",
        "wind_chill_f",
        "humidity_percent",
        "pressure_in",
        "visibility_mi",
        "wind_speed_mph",
        "precipitation_in",
    ]
    categorical_features = ["Side", "City",
                            "County", "Weather_Condition", "State"]

    encoded_df = preprocess_numerical_features(df, numerical_features)
    encoded_df = encode_timestamp_features(encoded_df)
    encoded_df = impute_categorical_features(encoded_df, categorical_features)
    encoded_df = encode_categorical_features(encoded_df)
    encoded_df = encode_spatial_features(encoded_df)
    encoded_df = cleanup_data(encoded_df)
    encoded_df.limit(1).show(vertical=True)

    # Train test data split
    print("\n\n\nSplitting data into train and test sets...")
    train, test = prepare_and_save_data(encoded_df)

    # Train 1st model
    print("\n\n\nTraining model 1...")
    predictions1, model_data1 = train_lr_with_grid_search(train, test)

    # Save 1st model best settings and metrics to hdfs and locally
    save_metrics_to_csv(
        metrics_list=[model_data1],
        local_file_path="/home/team13/project/BigData-Car-Accident/output/evaluation.csv",
        hdfs_file_path="/user/team13/project/output/evaluation",
    )

    # Save 1st model test predictions to hdfs and locally
    save_predictions(
        predictions1,
        hdfs_path="/user/team13/project/output/model1_predictions",
        local_path="/home/team13/project/BigData-Car-Accident/output/model1_predictions.csv",
    )

    # Train 2nd model
    print("\n\n\nTraining model 2...")
    predictions2, model_data2 = train_dt_with_grid_search(train, test)

    # Save 2nd model best settings and metrics to hdfs and locally
    save_metrics_to_csv(
        metrics_list=[model_data1, model_data2],
        local_file_path="/home/team13/project/BigData-Car-Accident/output/evaluation.csv",
        hdfs_file_path="/user/team13/project/output/evaluation",
    )

    # Save 2nd model test predictions to hdfs and locally
    save_predictions(
        predictions2,
        hdfs_path="/user/team13/project/output/model2_predictions",
        local_path="/home/team13/project/BigData-Car-Accident/output/model2_predictions.csv",
    )


if __name__ == "__main__":
    main()
