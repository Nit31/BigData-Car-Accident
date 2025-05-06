#!/bin/bash

password=$(head -n 1 secrets/.hive.pass)


hdfs dfs -rm project/output/q1/*
hdfs dfs -rm project/hive/warehouse/q1/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q1.hql

rm output/q1.csv
echo "day_of_week,accident_count,ratio_percent" > output/q1.csv 

hdfs dfs -cat project/output/q1/* >> output/q1.csv