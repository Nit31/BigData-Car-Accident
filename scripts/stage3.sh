#!/bin/bash
export HADOOP_CONF_DIR=/etc/hadoop/conf/

# Train models
spark-submit --master yarn scripts/model.py

# Copy models to the local repository
if [ -d "/home/team13/project/BigData-Car-Accident/models/model1" ]; then
    rm -rf model1
fi

if [ -d "/home/team13/project/BigData-Car-Accident/models/model2" ]; then
    rm -rf model2
fi

hdfs dfs -get  /user/team13/project/models/* /home/team13/project/BigData-Car-Accident/models/