The Final Project of Big Data Course
========================

The aim of this project is to create an end-to-end big data pipeline that ingests the large data from PostgreSQL into HDFS followed by analyzing the distributed data using Spark Engine and presenting the results in an Apache Superset dashboard.

The dataset used in this project is the [us-accidents](https://huggingface.co/datasets/nateraw/us-accidents) dataset from HuggingFace. The dataset contains information about accidents in the United States from 2016 to 2021.

## Project pipeline
### Stage 1
Data ingestion and preparation. Downloads the US accidents dataset, sanitizes column names, loads data into PostgreSQL, and transfers it to HDFS using Sqoop in Parquet format for distributed processing.

### Stage 2
Data analysis using Hive. Executes multiple HiveQL queries against the Parquet data to analyze accident patterns by various dimensions (time, location, weather conditions, severity) and exports results as CSV files.

### Stage 3
Machine learning with Spark. Trains predictive models using Spark ML on the distributed dataset and saves the trained models to both HDFS and local storage for later use.

### Stage 4

## How to run the project
1. Create `.psql.pass` and `.hive.pass` files in `secrets/` folder.
2. Run `bash main.sh`

## Project Structure
- `data/` contains the dataset files (original and processed).
- `models/` contains the Spark ML models.
- `notebooks/` contains the Jupyter notebook with the training and evaluation of the models.
- `output/` stores the results of the project.
- `scripts/` stores all the scripts of the pipeline.
- `sql/` is a folder for keeping all `.sql` and `.hql` files.
- `requirements.txt` lists the Python packages needed for running your Python scripts.
- `main.sh` is the main script that will run all scripts of the pipeline stages which will execute the full pipeline and store the results in `output/` folder.

