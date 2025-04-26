SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;

DROP TABLE IF EXISTS accidents_partitioned_bucketed;

-- Create new external table partitioned by State and bucketed by Severity
CREATE EXTERNAL TABLE accidents_partitioned_bucketed (
    ID                 STRING,
    Severity           INT,
    Start_Time         TIMESTAMP,
    End_Time           TIMESTAMP,
    Start_Lat          DECIMAL(10,7),
    Start_Lng          DECIMAL(10,7),
    End_Lat            DECIMAL(10,7),
    End_Lng            DECIMAL(10,7),
    Distance_mi        DECIMAL(10,2),
    Side               CHAR(1),
    City               STRING,
    County             STRING,
    Weather_Timestamp  TIMESTAMP,
    Temperature_F      DECIMAL(5,2),
    Wind_Chill_F       DECIMAL(5,2),
    Humidity_percent   INT,
    Pressure_in        DECIMAL(5,2),
    Visibility_mi      DECIMAL(5,2),
    Wind_Speed_mph     DECIMAL(6,2),
    Precipitation_in   DECIMAL(5,2),
    Weather_Condition  STRING,
    State              STRING
)
PARTITIONED BY (Sunrise_Sunset STRING)
CLUSTERED BY (Humidity_percent) INTO 5 BUCKETS
STORED AS PARQUET
LOCATION '/user/team13/project/warehouse/accidents_partitioned'
TBLPROPERTIES ("parquet.compress"="GZIP");

INSERT OVERWRITE TABLE accidents_partitioned_bucketed PARTITION (Sunrise_Sunset)
SELECT
    ID,
    Severity,
    cast(from_unixtime(CAST(Start_Time/1000 AS BIGINT), 'yyyy-MM-dd HH:mm:ss.SSS') AS TIMESTAMP) AS Start_Time,
    cast(from_unixtime(CAST(End_Time/1000   AS BIGINT), 'yyyy-MM-dd HH:mm:ss.SSS') AS TIMESTAMP) AS End_Time,
    cast(Start_Lat        AS DECIMAL(10,7))               AS Start_Lat,
    cast(Start_Lng        AS DECIMAL(10,7))               AS Start_Lng,
    cast(End_Lat          AS DECIMAL(10,7))               AS End_Lat,
    cast(End_Lng          AS DECIMAL(10,7))               AS End_Lng,
    cast(Distance_mi      AS DECIMAL(10,2))               AS Distance_mi,
    Side,
    City,
    County,
    cast(from_unixtime(CAST(Weather_Timestamp/1000 AS BIGINT), 'yyyy-MM-dd HH:mm:ss.SSS') AS TIMESTAMP) AS Weather_Timestamp,
    cast(Temperature_F    AS DECIMAL(5,2))               AS Temperature_F,
    cast(Wind_Chill_F     AS DECIMAL(5,2))               AS Wind_Chill_F,
    cast(Humidity_percent AS INT)                        AS Humidity_percent,
    cast(Pressure_in      AS DECIMAL(5,2))               AS Pressure_in,
    cast(Visibility_mi    AS DECIMAL(5,2))               AS Visibility_mi,
    cast(Wind_Speed_mph   AS DECIMAL(6,2))               AS Wind_Speed_mph,
    cast(Precipitation_in AS DECIMAL(5,2))               AS Precipitation_in,
    Weather_Condition,
    State,
    Sunrise_Sunset
FROM accidents_new;
