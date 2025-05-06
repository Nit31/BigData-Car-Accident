USE team13_projectdb;

DROP TABLE IF EXISTS q4_results;

CREATE EXTERNAL TABLE q4_results (
    Humidity_Bucket INT,
    Severity INT,
    Ratio_Percent FLOAT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q4';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q4_results
SELECT 
    humidity_lower + 5 AS Humidity_Bucket,
    Severity,
    ROUND((severity_count / total_per_bucket * 100), 2) AS Ratio_Percent
FROM (
    SELECT 
        FLOOR(LEAST(CAST(Humidity_percent AS FLOAT), 99.999) / 10) * 10 AS humidity_lower,
        Severity,
        COUNT(*) AS severity_count,
        SUM(COUNT(*)) OVER (PARTITION BY FLOOR(LEAST(CAST(Humidity_percent AS FLOAT), 99.999) / 10) * 10) AS total_per_bucket
    FROM accidents_partitioned_bucketed
    WHERE 
        Humidity_percent IS NOT NULL
        AND Severity IS NOT NULL
        AND CAST(Humidity_percent AS FLOAT) BETWEEN 0 AND 100
    GROUP BY FLOOR(LEAST(CAST(Humidity_percent AS FLOAT), 99.999) / 10) * 10, Severity
) aggregated
ORDER BY humidity_lower, Severity;

INSERT OVERWRITE DIRECTORY 'project/output/q4' 
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' 
SELECT * FROM q4_results;
