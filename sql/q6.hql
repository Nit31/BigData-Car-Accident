USE team13_projectdb;

DROP TABLE IF EXISTS q6_results;

CREATE EXTERNAL TABLE q6_results (
    City STRING,
    Accident_Count INT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q6';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q6_results
SELECT 
    standardized_city AS City,
    COUNT(*) AS Accident_Count
FROM (
    SELECT 
        UPPER(TRIM(City)) AS standardized_city
    FROM accidents_partitioned_bucketed
    WHERE 
        City IS NOT NULL 
        AND City != ''
) cleaned
GROUP BY standardized_city
ORDER BY Accident_Count DESC;

INSERT OVERWRITE DIRECTORY 'project/output/q6'  
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
SELECT * FROM q6_results;