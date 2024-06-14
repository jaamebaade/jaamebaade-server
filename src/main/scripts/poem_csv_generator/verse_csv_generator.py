import psycopg2
import csv

# Database connection parameters
dbname = 'jaamebaade'
user = 'jaamebaade_server_user'
password = 'jaamebaade_server_password'
host = 'localhost'
port = '5435'

# get ids
sql_query = 'SELECT c.poet_id, c.id, p.id FROM categories c join poems p on c.id = p.category_id;'

conn = psycopg2.connect(dbname=dbname, user=user, password=password, host=host, port=port)
# Create a cursor object
cur = conn.cursor()

# Execute the SQL query
cur.execute(sql_query)

# Fetch the results
poet_id_category_id_poem_id_list = cur.fetchall()

cur.close()

for i in poet_id_category_id_poem_id_list:
    # SQL query to select rows
    sql_query = f'SELECT * FROM verses WHERE poem_id = {i[2]}'

    # Create a cursor object
    cur = conn.cursor()

    # Execute the SQL query
    cur.execute(sql_query)

    # Fetch the results
    rows = cur.fetchall()


    # Specify the CSV file name
    csv_file_name = f'verses_{i[0]}.csv'

    # Open a new CSV file in write mode
    with open(csv_file_name, mode='a', newline='') as csv_file:
        # Create a CSV writer object
        csv_writer = csv.writer(csv_file)
        
        # Check if file already exists
        if csv_file.tell() == 0:
            # Write the header (column names)
            column_names = [desc[0] for desc in cur.description]
            csv_writer.writerow(column_names)
        
        # Iterate over the query results and write each row to the CSV file
        for row in rows:
            csv_writer.writerow(row)

# Close the cursor and the database connection
cur.close()
conn.close()