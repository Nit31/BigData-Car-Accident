USE team13_projectdb;

DROP TABLE IF EXISTS normalized_metrics;

CREATE EXTERNAL TABLE normalized_metrics (
    model_name STRING,
    f1_score DOUBLE,
    accuracy DOUBLE,
    class INT,
    prec DOUBLE,
    recall DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/user/team13/project/output/normalized_metrics'
TBLPROPERTIES ("skip.header.line.count"="0");

INSERT OVERWRITE TABLE normalized_metrics
SELECT
  model_name,
  f1_score,
  accuracy,
  CAST(stack_values.class_str AS INT) AS class,
  stack_values.prec,
  stack_values.rec
FROM evaluation_metrics
LATERAL VIEW STACK(
    4,
    '1', precision_1, recall_1,
    '2', precision_2, recall_2,
    '3', precision_3, recall_3,
    '4', precision_4, recall_4
) stack_values AS class_str, prec, rec;
