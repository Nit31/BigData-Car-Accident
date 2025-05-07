This directory contains all scripts used in the project pipeline. These scripts handle data collection, processing, modeling, and visualization across the four stages of the project.

### Core Pipeline Scripts

- `stage1.sh` - Manages data collection, preprocessing, and loading to HDFS via PostgreSQL and Sqoop
- `stage2.sh` - Executes Hive queries for data analysis and saves results as CSV files
- `stage3.sh` - Trains machine learning models using Spark and saves them locally
- `stage4.sh` - Saves evaluation metrics from the stage 3 to a Hive external table.

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
