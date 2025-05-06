#!/bin/bash

password=$(head -n 1 secrets/.hive.pass)


hdfs dfs -rm project/output/q5/*
hdfs dfs -rm project/hive/warehouse/q5/*
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team13 -p $password -f sql/q5.hql


rm output/q5.csv
echo "Year,Accident_count" > output/q5.csv 

hdfs dfs -cat project/output/q5/* >> output/q5.csv