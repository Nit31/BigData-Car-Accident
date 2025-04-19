#!/bin/bash

# Activate the virtual environment
source ./venv/bin/activate

# Run the Python script to create and load the PostgreSQL database
python3 -m scripts.build_projectdb

# Clear the HDFS directory if it exists
hdfs dfs -rm -r -f /user/team13/project/warehouse

# Import the data from PostgreSQL to HDFS using Sqoop
password=$(head -n 1 secrets/.psql.pass)
sqoop import --connect jdbc:postgresql://hadoop-04.uni.innopolis.ru/team13_projectdb \
--username team13 --password $password --compression-codec=gzip --compress \
--as-parquetfile --warehouse-dir=/user/team13/project/warehouse --m 1 --table accidents