USE team13_projectdb;

DROP TABLE IF EXISTS q7_results;

CREATE EXTERNAL TABLE q7_results (
    Severity_Level STRING,
    Accident_Count INT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q7';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q7_results
SELECT 
    Severity AS Severity_Level,
    COUNT(*) AS Accident_Count
FROM 
    accidents_partitioned_bucketed
WHERE 
    Severity IS NOT NULL
GROUP BY 
    Severity
ORDER BY 
    Accident_Count DESC;

INSERT OVERWRITE DIRECTORY 'project/output/q7'  
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
SELECT * FROM q7_results;