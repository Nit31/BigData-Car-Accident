
# Scripts Documentation

This directory contains all scripts used in the project pipeline. These scripts handle data collection, processing, modeling, and visualization across the four stages of the project.

## Pipeline Overview

The project is organized into four sequential stages:

1. **Stage 1**: Data ingestion and preparation - Downloads the US accidents dataset, sanitizes column names, loads data into PostgreSQL, and transfers to HDFS using Sqoop
2. **Stage 2**: Data analysis using Hive - Executes analytical queries on the dataset stored in HDFS
3. **Stage 3**: Machine learning with Spark - Trains predictive models using SparkML
4. **Stage 4**: 

## Script Categories

### Core Pipeline Scripts

- `stage1.sh` - Manages data collection, preprocessing, and loading to HDFS via PostgreSQL and Sqoop
- `stage2.sh` - Executes Hive queries for data analysis and saves results as CSV files
- `stage3.sh` - Trains machine learning models using Spark and saves them locally
- `stage4.sh` - Sets up and runs the Streamlit visualization app

### Data Preprocessing Scripts

- `preprocess.sh` - Creates and configures Python virtual environment with required packages
- `data_collection.sh` - Downloads the US accidents dataset from HuggingFace
- `build_projectdb.py` - Creates the PostgreSQL database schema and loads the data 

### Modeling Scripts

- `model.py` - Main Python script for ML modeling that:
  - Loads and preprocesses data for machine learning
  - Implements feature engineering (numerical, categorical, timestamp, spatial)
  - Trains and tunes models (Logistic Regression and Decision Tree)
  - Evaluates model performance
  - Saves models, predictions, and evaluation metrics

## Usage

The entire pipeline can be executed by running the main script from the project root:

```bash
bash main.sh