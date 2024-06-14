import psycopg2
import csv

# Database connection parameters
dbname = 'jaamebaade'
user = 'jaamebaade_server_user'
password = 'jaamebaade_server_password'
host = 'localhost'
port = '5435'

# get ids
sql_query = 'SELECT DISTINCT poet_id FROM categories'

conn = psycopg2.connect(dbname=dbname, user=user, password=password, host=host, port=port)
# Create a cursor object
cur = conn.cursor()

# Execute the SQL query
cur.execute(sql_query)

# Fetch the results
poet_ids = list(map(lambda x: x[0], cur.fetchall()))

cur.close()

for i in poet_ids:
    # SQL query to select rows
    sql_query = f'SELECT * FROM categories WHERE poet_id = {i}'

    # Create a cursor object
    cur = conn.cursor()

    # Execute the SQL query
    cur.execute(sql_query)

    # Fetch the results
    rows = cur.fetchall()


    # Specify the CSV file name
    csv_file_name = f'category_{i}.csv'

    # Open a new CSV file in write mode
    with open(csv_file_name, mode='w', newline='') as csv_file:
        # Create a CSV writer object
        csv_writer = csv.writer(csv_file)
        
        # Write the header (column names)
        column_names = [desc[0] for desc in cur.description]
        csv_writer.writerow(column_names)
        
        # Iterate over the query results and write each row to the CSV file
        for row in rows:
            csv_writer.writerow(row)

# Close the cursor and the database connection
cur.close()
conn.close()