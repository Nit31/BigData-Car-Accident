#!/bin/bash

sed -E -i '' \
  -e '1s/Distance\(mi\)/Distance_mi/' \
  -e '1s/Weather_Timestamp\?/Weather_Timestamp/' \
  -e '1s/Temperature\(F\)/Temperature_F/' \
  -e '1s/Wind_Chill\(F\)/Wind_Chill_F/' \
  -e '1s/Humidity\(%\)/Humidity_percent/' \
  -e '1s/Pressure\(in\)/Pressure_in/' \
  -e '1s/Visibility\(mi\)/Visibility_mi/' \
  -e '1s/Wind_Speed\(mph\)/Wind_Speed_mph/' \
  -e '1s/Precipitation\(in\)/Precipitation_in/' \
  data/data.csv