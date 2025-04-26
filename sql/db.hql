SHOW DATABASES;

DROP DATABASE IF EXISTS team13_projectdb CASCADE;

CREATE DATABASE team13_projectdb LOCATION "project/hive/warehouse";

USE team13_projectdb;

SHOW TABLES;

CREATE EXTERNAL TABLE accidents_new(
    ID STRING,
    Severity INT,
    Start_Time BIGINT,
    End_Time BIGINT,
    Start_Lat STRING,
    Start_Lng STRING,
    End_Lat STRING,
    End_Lng STRING,
    Distance_mi STRING,
    Side CHAR(1),
    City STRING,
    County STRING,
    State CHAR(2),
    Weather_Timestamp BIGINT,
    Temperature_F STRING,
    Wind_Chill_F STRING,
    Humidity_percent STRING,
    Pressure_in STRING,
    Visibility_mi STRING,
    Wind_Speed_mph STRING,
    Precipitation_in STRING,
    Weather_Condition STRING,
    Sunrise_Sunset STRING
)
    STORED AS PARQUET
    LOCATION '/user/team13/project/warehouse/accidents'
    tblproperties ("parquet.compress"="GZIP");

SHOW TABLES;
