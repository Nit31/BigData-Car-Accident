#!/bin/bash

password=$(head -n 1 secrets/.hive.pass)


hdfs dfs -rm project/output/q6/*
hdfs dfs -rm project/hive/warehouse/q6/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q6.hql


rm output/q6.csv
echo "City,Accident_count" > output/q6.csv 

hdfs dfs -cat project/output/q6/* >> output/q6.csv