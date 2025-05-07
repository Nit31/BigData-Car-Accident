USE team13_projectdb;

DROP TABLE IF EXISTS q9_results;

CREATE EXTERNAL TABLE q9_results (
    Hour_Of_Day INT,
    Is_Weekend BOOLEAN,
    Severity_Level INT,
    Accident_Count INT,
    Severity_Ratio_Percent FLOAT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q9';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q9_results
SELECT 
    hour_of_day,
    is_weekend,
    Severity,
    accident_count,
    ROUND((accident_count / hourly_total * 100), 2) AS severity_ratio_percent
FROM (
    SELECT 
        hour_of_day,
        is_weekend,
        Severity,
        accident_count,
        SUM(accident_count) OVER (PARTITION BY hour_of_day, is_weekend) AS hourly_total
    FROM (
        SELECT 
            HOUR(Start_Time) AS hour_of_day,
            DAYOFWEEK(Start_Time) IN (1, 7) AS is_weekend,
            Severity,
            COUNT(*) AS accident_count
        FROM accidents_partitioned_bucketed
        WHERE 
            Start_Time IS NOT NULL
            AND Severity IS NOT NULL
        GROUP BY 
            HOUR(Start_Time),
            DAYOFWEEK(Start_Time) IN (1, 7),
            Severity
    ) raw_counts
) with_totals
ORDER BY 
    hour_of_day,
    is_weekend,
    Severity;

INSERT OVERWRITE DIRECTORY 'project/output/q9'  
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
SELECT * FROM q9_results;ы