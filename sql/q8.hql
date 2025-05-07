USE team13_projectdb;

DROP TABLE IF EXISTS q8_results;

CREATE EXTERNAL TABLE q8_results (
    Day_Of_Week STRING,
    Severity_Level INT,
    Accident_Count INT,
    Daily_Proportion_Percent FLOAT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q8';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q8_results
SELECT 
    day_of_week,
    Severity,
    accident_count,
    ROUND((accident_count / daily_total * 100), 2) AS daily_proportion_percent
FROM (
    SELECT 
        day_of_week,
        Severity,
        accident_count,
        SUM(accident_count) OVER (PARTITION BY day_of_week) AS daily_total
    FROM (
        SELECT 
            CASE DAYOFWEEK(Start_time)
                WHEN 1 THEN 7  -- Sunday
                WHEN 2 THEN 1  -- Monday
                WHEN 3 THEN 2  -- Tuesday
                WHEN 4 THEN 3  -- Wednesday
                WHEN 5 THEN 4  -- Thursday
                WHEN 6 THEN 5  -- Friday
                WHEN 7 THEN 6  -- Saturday
            END AS day_of_week,
            Severity,
            COUNT(*) AS accident_count
        FROM accidents_partitioned_bucketed
        WHERE 
            Start_Time IS NOT NULL
            AND Severity IS NOT NULL
        GROUP BY 
            CASE DAYOFWEEK(Start_time)
                WHEN 1 THEN 7
                WHEN 2 THEN 1
                WHEN 3 THEN 2
                WHEN 4 THEN 3
                WHEN 5 THEN 4
                WHEN 6 THEN 5
                WHEN 7 THEN 6
            END,
            Severity
    ) daily_severity_counts
) with_daily_totals
ORDER BY 
    day_of_week, 
    Severity;

INSERT OVERWRITE DIRECTORY 'project/output/q8'  
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
SELECT * FROM q8_results;