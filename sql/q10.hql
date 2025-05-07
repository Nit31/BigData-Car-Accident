USE team13_projectdb;

DROP TABLE IF EXISTS q10_results;

CREATE EXTERNAL TABLE q10_results (
    Year INT,
    Severity_Level INT,
    Accident_Count INT,
    Severity_Ratio_Percent FLOAT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q10';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q10_results
SELECT 
    accident_year,
    Severity,
    yearly_severity_count,
    ROUND((yearly_severity_count / yearly_total * 100), 2) AS severity_ratio_percent
FROM (
    SELECT 
        accident_year,
        Severity,
        yearly_severity_count,
        SUM(yearly_severity_count) OVER (PARTITION BY accident_year) AS yearly_total
    FROM (
        SELECT 
            YEAR(Start_time) AS accident_year,
            Severity,
            COUNT(*) AS yearly_severity_count
        FROM accidents_partitioned_bucketed
        WHERE 
            Start_Time IS NOT NULL
            AND Severity IS NOT NULL
        GROUP BY 
            YEAR(Start_time),
            Severity
    ) yearly_counts
) with_yearly_totals
ORDER BY 
    accident_year,
    Severity;

INSERT OVERWRITE DIRECTORY 'project/output/q10'  
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
SELECT * FROM q10_results;