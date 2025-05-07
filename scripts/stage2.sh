#!/bin/bash

password=$(head -n 1 secrets/.hive.pass)

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/db.hql > output/hive_results.txt

# Query 1
hdfs dfs -rm project/output/q1/*
hdfs dfs -rm project/hive/warehouse/q1/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q1.hql

rm output/q1.csv
echo "day_of_week,accident_count,ratio_percent" > output/q1.csv 

hdfs dfs -cat project/output/q1/* >> output/q1.csv

# Query 2
hdfs dfs -rm project/output/q2/*
hdfs dfs -rm project/hive/warehouse/q2/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q2.hql

rm output/q2.csv
echo "Hour_Of_Day,is_weekend,accident_count,ratio_percent" > output/q2.csv 

hdfs dfs -cat project/output/q2/* >> output/q2.csv

# Query 3
hdfs dfs -rm project/output/q3/*
hdfs dfs -rm project/hive/warehouse/q3/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q3.hql

rm output/q3.csv
echo "temperature,Severity_1_Count,Severity_2_Count,Severity_3_Count,Severity_4_Count" > output/q3.csv 

hdfs dfs -cat project/output/q3/* >> output/q3.csv


# Query 4
hdfs dfs -rm project/output/q4/*
hdfs dfs -rm project/hive/warehouse/q4/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q4.hql


rm output/q4.csv
echo "humidity,severity,ratio_percent" > output/q4.csv 

hdfs dfs -cat project/output/q4/* >> output/q4.csv


# Query 5
hdfs dfs -rm project/output/q5/*
hdfs dfs -rm project/hive/warehouse/q5/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q5.hql


rm output/q5.csv
echo "Year,Accident_count" > output/q5.csv 

hdfs dfs -cat project/output/q5/* >> output/q5.csv

# Query 6
hdfs dfs -rm project/output/q6/*
hdfs dfs -rm project/hive/warehouse/q6/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q6.hql


rm output/q6.csv
echo "City,Accident_count" > output/q6.csv 

hdfs dfs -cat project/output/q6/* >> output/q6.csv

# Query 7
hdfs dfs -rm project/output/q7/*
hdfs dfs -rm project/hive/warehouse/q7/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q7.hql

rm output/q7.csv
echo "Severity_Level,Accident_count" > output/q7.csv 

hdfs dfs -cat project/output/q7/* >> output/q7.csv

# Query 8
hdfs dfs -rm project/output/q8/*
hdfs dfs -rm project/hive/warehouse/q8/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q8.hql

rm output/q8.csv
echo "Day_Of_Week,Severity_Level,Accident_Count,Daily_Proportion_Percent" > output/q8.csv 

hdfs dfs -cat project/output/q8/* >> output/q8.csv

# Query 9
hdfs dfs -rm project/output/q9/*
hdfs dfs -rm project/hive/warehouse/q9/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q9.hql

rm output/q9.csv
echo "Day_Of_Week,Severity_Level,Accident_Count,Daily_Proportion_Percent" > output/q9.csv 

hdfs dfs -cat project/output/q9/* >> output/q9.csv

# Query 10
hdfs dfs -rm project/output/q10/*
hdfs dfs -rm project/hive/warehouse/q10/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q10.hql

rm output/q10.csv
echo "Year,Severity_Level,Accident_Count,Severity_Ratio_Percent" > output/q10.csv 

hdfs dfs -cat project/output/q10/* >> output/q10.csv

# Query 11
hdfs dfs -rm project/output/q11/*
hdfs dfs -rm project/hive/warehouse/q11/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q11.hql

rm output/q11.csv
echo "City,Severity_Level,Accident_Count,Severity_Ratio_Percent" > output/q11.csv 

hdfs dfs -cat project/output/q11/* >> output/q11.csv