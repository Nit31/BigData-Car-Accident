USE team13_projectdb;

DROP TABLE IF EXISTS q2_results;

CREATE EXTERNAL TABLE q2_results (
    Hour_Of_Day INT,
    Is_Weekend BOOLEAN,
    Accident_Count INT,
    Ratio_Percent FLOAT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q2';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q2_results
SELECT 
    hour_of_day,
    is_weekend,
    accident_count,
    ROUND((accident_count / total_count * 100), 2) AS ratio_percent
FROM (
    SELECT 
        hour_of_day,
        is_weekend,
        COUNT(*) AS accident_count,
        SUM(COUNT(*)) OVER(PARTITION BY is_weekend)  AS total_count
    FROM (
        SELECT 
            HOUR(Start_Time) AS hour_of_day,
            CASE 
                WHEN DAYOFWEEK(Start_Time) IN (1, 7) THEN TRUE 
                ELSE FALSE 
            END AS is_weekend
        FROM accidents_partitioned_bucketed
        WHERE Start_Time IS NOT NULL
    ) extracted
    GROUP BY hour_of_day, is_weekend
) aggregated
ORDER BY hour_of_day, is_weekend;

INSERT OVERWRITE DIRECTORY 'project/output/q2' 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
SELECT * FROM q2_results;