#!/bin/bash

password=$(head -n 1 secrets/.hive.pass)


hdfs dfs -rm project/output/q3/*
hdfs dfs -rm project/hive/warehouse/q3/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q3.hql

rm output/q3.csv
echo "temperature,Severity_1_Count,Severity_2_Count,Severity_3_Count,Severity_4_Count" > output/q3.csv 

hdfs dfs -cat project/output/q3/* >> output/q3.csv