#!/bin/bash

VENV_DIR="venv"

# Function to create Python 3.11 virtual environment
create_venv() {
    echo "Creating virtual environment with Python 3.11..."
    python3.11 -m venv "$VENV_DIR"
}

# Check if venv exists
if [ -d "$VENV_DIR" ] && [ -x "$VENV_DIR/bin/python" ]; then
    PYTHON_VERSION=$("$VENV_DIR/bin/python" -V 2>&1)
    if [[ "$PYTHON_VERSION" =~ Python\ 3\.11\.* ]]; then
        echo "Virtual environment already exists and uses Python 3.11."
    else
        echo "Virtual environment exists but is not using Python 3.11. Found: $PYTHON_VERSION"
        echo "Removing existing virtual environment..."
        rm -rf "$VENV_DIR"
        create_venv
    fi
else
    echo "No virtual environment found."
    create_venv
fi

# Activate the virtual environment
source ./venv/bin/activate

# Install the required packages
pip install --upgrade pip
pip install -r requirements.txt