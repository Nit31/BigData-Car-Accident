USE team13_projectdb;

DROP TABLE IF EXISTS q1_results;

CREATE EXTERNAL TABLE q1_results (
    Day_Of_Week STRING,
    Accident_Count INT,
    Ratio_Percent FLOAT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q1';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q1_results
SELECT 
    day_of_week,
    accident_count,
    ROUND((accident_count / total_count * 100), 2) AS ratio_percent
FROM (
    SELECT 
        day_of_week,
        COUNT(*) AS accident_count,
        SUM(COUNT(*)) OVER() AS total_count
    FROM (
        SELECT 
            CASE DAYOFWEEK(Start_time)
                WHEN 1 THEN 7
                WHEN 2 THEN 1
                WHEN 3 THEN 2
                WHEN 4 THEN 3
                WHEN 5 THEN 4
                WHEN 6 THEN 5
                WHEN 7 THEN 6
            END AS day_of_week
        FROM accidents_partitioned_bucketed
        WHERE Start_Time IS NOT NULL
    ) day_extracted
    GROUP BY day_of_week
) aggregated
ORDER BY day_of_week;

INSERT OVERWRITE DIRECTORY 'project/output/q1' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q1_results;