import csv
import io
import os
from pprint import pprint

import psycopg2 as psql


def main():
    # Read password from secrets file
    file = os.path.join("secrets", ".psql.pass")
    with open(file, "r") as file:
        password = file.read().rstrip()

    # build connection string
    conn_string = f"host=hadoop-04.uni.innopolis.ru port=5432 user=team13 dbname=team13_projectdb password={password}"

    # Connect to the remote dbms
    with psql.connect(conn_string) as conn:
        # Create a cursor for executing psql commands
        cur = conn.cursor()
        # Read the commands from the file and execute them.
        with open(os.path.join("sql", "create_tables.sql")) as file:
            content = file.read()
            cur.execute(content)
        conn.commit()

        # Read the commands from the file and execute them.
        with open(os.path.join("sql", "import_data.sql")) as file:
            sql_copy = file.read()
            # Filter and reorder CSV columns to match the COPY definition
            columns = [
                'ID', 'Severity', 'Start_Time', 'End_Time',
                'Start_Lat', 'Start_Lng', 'End_Lat', 'End_Lng',
                'Distance_mi', 'Side', 'City', 'County', 'State',
                'Weather_Timestamp', 'Temperature_F', 'Wind_Chill_F',
                'Humidity_percent', 'Pressure_in', 'Visibility_mi',
                'Wind_Speed_mph', 'Precipitation_in', 'Weather_Condition', 'Sunrise_Sunset'
            ]
            with open(os.path.join("data", "data.csv"), "r") as infile:
                reader = csv.DictReader(infile)
                buffer = io.StringIO()
                writer = csv.writer(buffer)
                writer.writerow(columns)
                for row in reader:
                    values = []
                    for col in columns:
                        raw_val = row.get(col)
                        # Treat missing keys or blank values as NULL
                        if raw_val is None or raw_val.strip() == "":
                            values.append('null')
                        else:
                            values.append(raw_val.strip())
                    writer.writerow(values)
                buffer.seek(0)
                cur.copy_expert(sql_copy, buffer)

        # If the sql statements are CRUD then commit the change
        conn.commit()

        pprint(conn)
        cur = conn.cursor()
        # Read the sql commands from the file
        with open(os.path.join("sql", "test_database.sql")) as file:
            commands = file.readlines()
            for command in commands:
                cur.execute(command)
                # Read all records and print them
                pprint(cur.fetchall())


if __name__ == "__main__":
    main()
