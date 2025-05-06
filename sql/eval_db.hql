CREATE EXTERNAL TABLE evaluation_metrics (
    accuracy DOUBLE,
    f1_score DOUBLE,
    model_name STRING,
    precision_1 DOUBLE,
    precision_2 DOUBLE,
    precision_3 DOUBLE,
    precision_4 DOUBLE,
    recall_1 DOUBLE,
    recall_2 DOUBLE,
    recall_3 DOUBLE,
    recall_4 DOUBLE
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = "\"",
    "escapeChar" = "\\"
)
STORED AS TEXTFILE
LOCATION '/user/team13/project/output/evaluation'
TBLPROPERTIES ("skip.header.line.count"="1");