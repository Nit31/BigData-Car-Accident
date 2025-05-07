USE team13_projectdb;

DROP TABLE IF EXISTS q5_results;

CREATE EXTERNAL TABLE q5_results (
    Year INT,
    Accident_Count INT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q5';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q5_results
SELECT 
    YEAR(Start_time) AS Year,
    COUNT(*) AS Accident_Count
FROM accidents_partitioned_bucketed
WHERE 
    Start_Time IS NOT NULL
GROUP BY YEAR(Start_time)
ORDER BY Year;

INSERT OVERWRITE DIRECTORY 'project/output/q5' 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
SELECT * FROM q5_results;