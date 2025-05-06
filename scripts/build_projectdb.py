"""
Script to build and populate the project database with car accident data.
"""

import csv
import io
import os
from pprint import pprint

import psycopg2 as psql
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT


def main():
    """
    Main function to connect to PostgreSQL database, create tables,
    import data from CSV, and run test queries.
    """
    # Read password from secrets file
    file = os.path.join("secrets", ".psql.pass")
    with open(file, "r", encoding="utf-8") as file:
        password = file.read().rstrip()

    # Connection with extended timeout
    conn_string = (
        f"host=hadoop-04.uni.innopolis.ru port=5432 "
        f"user=team13 dbname=team13_projectdb password={password} "
        f"connect_timeout=120 "
        f"client_encoding=UTF8"
    )

    with psql.connect(conn_string) as conn:
        conn.set_isolation_level(ISOLATION_LEVEL_AUTOCOMMIT)
        cur = conn.cursor()

        with open(os.path.join("sql", "create_tables.sql"), encoding="utf-8") as file:
            content = file.read()
            cur.execute(content)

        # Process the large CSV file in chunks
        chunk_size = 100000
        columns = [
            "ID",
            "Severity",
            "Start_Time",
            "End_Time",
            "Start_Lat",
            "Start_Lng",
            "End_Lat",
            "End_Lng",
            "Distance_mi",
            "Side",
            "City",
            "County",
            "State",
            "Weather_Timestamp",
            "Temperature_F",
            "Wind_Chill_F",
            "Humidity_percent",
            "Pressure_in",
            "Visibility_mi",
            "Wind_Speed_mph",
            "Precipitation_in",
            "Weather_Condition",
            "Sunrise_Sunset",
        ]

        # Read the SQL COPY command
        with open(os.path.join("sql", "import_data.sql"), encoding="utf-8") as file:
            sql_copy = file.read()

        # Process the CSV in chunks
        with open(os.path.join("data", "data.csv"), "r", encoding="utf-8") as infile:
            reader = csv.DictReader(infile)

            batch = []
            row_count = 0
            for row_count, row in enumerate(reader):
                values = []
                for col in columns:
                    raw_val = row.get(col)
                    # Treat missing keys or blank values as NULL
                    if raw_val is None or raw_val.strip() == "":
                        values.append("null")
                    else:
                        values.append(raw_val.strip())

                batch.append(values)

                # When we reach chunk_size, process the batch
                if len(batch) >= chunk_size:
                    _process_batch(conn, cur, sql_copy, batch)
                    batch = []
                    print(f"Processed {row_count + 1} rows")

            # Process any remaining rows
            if batch:
                _process_batch(conn, cur, sql_copy, batch)
                print(f"Processed all {row_count + 1} rows")

        # Run test queries
        print("Running test queries:")
        cur = conn.cursor()
        with open(os.path.join("sql", "test_database.sql"), encoding="utf-8") as file:
            commands = file.readlines()
            for command in commands:
                if command.strip():
                    cur.execute(command)
                    pprint(cur.fetchall())


def _process_batch(conn, cur, sql_copy, batch):
    """
    Process a batch of rows using the COPY command.

    Args:
        conn: Database connection
        cur: Database cursor
        sql_copy: SQL COPY command to execute
        batch: List of rows to process
    """
    buffer = io.StringIO()
    writer = csv.writer(buffer)

    # Write rows to buffer
    for row in batch:
        writer.writerow(row)

    # Reset buffer position for reading
    buffer.seek(0)

    # Execute COPY command for this batch
    cur.copy_expert(sql_copy, buffer)
    conn.commit()


if __name__ == "__main__":
    main()
