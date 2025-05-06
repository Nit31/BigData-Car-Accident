#!/bin/bash

password=$(head -n 1 secrets/.hive.pass)


hdfs dfs -rm project/output/q4/*
hdfs dfs -rm project/hive/warehouse/q4/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q4.hql


rm output/q4.csv
echo "humidity,severity,ratio_percent" > output/q4.csv 

hdfs dfs -cat project/output/q4/* >> output/q4.csv