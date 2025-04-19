#!/bin/bash

# Activate the virtual environment
source ./venv/bin/activate

# Install the required packages
pip install --upgrade pip
pip install -r requirements.txt

# Run the Python script to create and load the PostgreSQL database
python3 -m scripts.build_projectdb.py