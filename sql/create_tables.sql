START TRANSACTION;

DROP TABLE IF EXISTS accidents CASCADE;

-- Create the accidents table
CREATE TABLE IF NOT EXISTS accidents (
    ID VARCHAR(9) NOT NULL PRIMARY KEY,
    Severity INT NOT NULL,
    Start_Time TIMESTAMP NOT NULL,
    End_Time TIMESTAMP NOT NULL,
    Start_Lat DECIMAL(10, 7) NOT NULL,
    Start_Lng DECIMAL(10, 7) NOT NULL,
    End_Lat DECIMAL(10, 7),
    End_Lng DECIMAL(10, 7),
    Distance_mi DECIMAL(10, 2) NOT NULL,
    Side CHAR(1),
    City VARCHAR(255),
    County VARCHAR(255),
    State CHAR(2),
    Weather_Timestamp TIMESTAMP,
    Temperature_F DECIMAL(5, 2),
    Wind_Chill_F DECIMAL(5, 2),
    Humidity_percent DECIMAL(5, 2),
    Pressure_in DECIMAL(5, 2),
    Visibility_mi DECIMAL(5, 2),
    Wind_Speed_mph DECIMAL(5, 2),
    Precipitation_in DECIMAL(5, 2),
    Weather_Condition VARCHAR(255),
    Sunrise_Sunset VARCHAR(50)
);

ALTER DATABASE team13_projectdb SET datestyle TO iso, ymd;

COMMIT;