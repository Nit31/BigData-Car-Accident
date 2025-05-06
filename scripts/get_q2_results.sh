#!/bin/bash

password=$(head -n 1 secrets/.hive.pass)


hdfs dfs -rm project/output/q2/*
hdfs dfs -rm project/hive/warehouse/q2/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q2.hql

rm output/q2.csv
echo "Hour_Of_Day,Accident_Count,ratio_percent" > output/q2.csv 

hdfs dfs -cat project/output/q2/* >> output/q2.csv