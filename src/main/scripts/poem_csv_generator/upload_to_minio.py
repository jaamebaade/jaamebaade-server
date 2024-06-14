from minio import Minio
from minio.error import S3Error
import os

# Create a MinIO client with the MinIO server endpoint, access key, and secret key.
minio_client = Minio(
    "185.8.174.93:9001",
    access_key="jaamebaade_server_user",
    secret_key="pass",
    secure=False  # Set to False if your MinIO server does not use SSL
)
def upload_file_to_minio(bucket_name, file_path, object_name):

    try:
        # Upload the file
        minio_client.fput_object(
            bucket_name, object_name, file_path
        )
        print(f"'{file_path}' is successfully uploaded as '{object_name}' to bucket '{bucket_name}'.")
    except S3Error as exc:
        print("Error occurred:", exc)

# Example usage
# Directory containing the zip files
directory = "../../resources/zips"

# Iterate over all files in the directory
for filename in os.listdir(directory):
    if filename.endswith(".zip"):
        # Construct the full file path
        file_path = os.path.join(directory, filename)
        
        # Call the upload_file_to_minio function
        upload_file_to_minio("zips", file_path, filename)