#!/bin/bash

# Activate the virtual environment
source ./venv/bin/activate

# Download the dataset
echo "Running data collection"
bash scripts/data_collection.sh

# Change column names to more convenient ones
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

# Run the Python script to create and load the PostgreSQL database
python3 -m scripts.build_projectdb

# Clear the HDFS directory if it exists
hdfs dfs -rm -r -f /user/team13/project/warehouse

# Import the data from PostgreSQL to HDFS using Sqoop
password=$(head -n 1 secrets/.psql.pass)
sqoop import --connect jdbc:postgresql://hadoop-04.uni.innopolis.ru/team13_projectdb \
--username team13 --password $password --compression-codec=gzip --compress \
--as-parquetfile --warehouse-dir=/user/team13/project/warehouse --m 1 --table accidents