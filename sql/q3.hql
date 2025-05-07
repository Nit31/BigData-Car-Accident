USE team13_projectdb;

DROP TABLE IF EXISTS q3_results;

CREATE EXTERNAL TABLE q3_results (
    Temp_Bucket INT,
    Severity_1_Count INT,
    Severity_2_Count INT,
    Severity_3_Count INT,
    Severity_4_Count INT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q3';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q3_results
SELECT 
    temp_lower + 2.5 AS Temp_Bucket,
    SUM(CASE WHEN Severity = 1 THEN 1 ELSE 0 END) AS Severity_1_Count,
    SUM(CASE WHEN Severity = 2 THEN 1 ELSE 0 END) AS Severity_2_Count,
    SUM(CASE WHEN Severity = 3 THEN 1 ELSE 0 END) AS Severity_3_Count,
    SUM(CASE WHEN Severity = 4 THEN 1 ELSE 0 END) AS Severity_4_Count
FROM (
    SELECT 
        FLOOR(CAST((Temperature_F - 32) * 5 / 9 AS FLOAT) / 5) * 5 AS temp_lower,
        Severity
    FROM accidents_partitioned_bucketed
    WHERE 
        Temperature_F IS NOT NULL
        AND Severity IS NOT NULL
) cleaned
GROUP BY temp_lower
ORDER BY temp_lower;

INSERT OVERWRITE DIRECTORY 'project/output/q3'  
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
SELECT * FROM q3_results;