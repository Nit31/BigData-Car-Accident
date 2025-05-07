#!/bin/bash

DATA_URL="https://huggingface.co/datasets/nateraw/us-accidents/resolve/main/US_Accidents_Dec21_updated.csv"

OUTPUT_FILE="./data/data.csv"

wget -O "${OUTPUT_FILE}" "${DATA_URL}"