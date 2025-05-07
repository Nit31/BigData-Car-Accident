USE team13_projectdb;

DROP TABLE IF EXISTS q11_results;

CREATE EXTERNAL TABLE q11_results (
    City STRING,
    Severity_Level INT,
    Accident_Count INT,
    Severity_Ratio_Percent FLOAT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q11';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q11_results
SELECT 
    standardized_city AS City,
    Severity,
    accident_count,
    ROUND((accident_count / city_total * 100), 2) AS severity_ratio_percent
FROM (
    SELECT 
        standardized_city,
        Severity,
        accident_count,
        SUM(accident_count) OVER (PARTITION BY standardized_city) AS city_total
    FROM (
        SELECT 
            UPPER(TRIM(City)) AS standardized_city,
            Severity,
            COUNT(*) AS accident_count
        FROM accidents_partitioned_bucketed
        WHERE 
            City IS NOT NULL 
            AND City != ''
            AND Severity IS NOT NULL
        GROUP BY 
            UPPER(TRIM(City)),
            Severity
    ) city_severity_counts
) with_city_totals
ORDER BY 
    City, 
    Severity;

INSERT OVERWRITE DIRECTORY 'project/output/q11'  
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
SELECT * FROM q11_results;