COPY accidents(
    ID, Severity, Start_Time, End_Time,
    Start_Lat, Start_Lng, End_Lat, End_Lng,
    Distance_mi, Side, City, County, State,
    Weather_Timestamp, Temperature_F, Wind_Chill_F,
    Humidity_percent, Pressure_in, Visibility_mi,
    Wind_Speed_mph, Precipitation_in, Weather_Condition,
    Sunrise_Sunset
)
FROM STDIN WITH CSV HEADER DELIMITER ',' NULL AS 'null';