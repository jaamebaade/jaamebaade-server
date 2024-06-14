#!/bin/bash

# Specify the folders where the files are located
folder1="../../resources/category-csvs"
folder2="../../resources/poem-csvs"
folder3="../../resources/verse-csvs"

# Loop through the files in folder1
for file1 in "$folder1"/*; do
    # Extract the ID from the filename
    id=$(basename "$file1" | sed 's/[a-zA-Z_\.]//g')
    
    # Find the corresponding files in folder2 and folder3
    file2="$folder2/poems_$id.csv"
    file3="$folder3/verses_$id.csv"
    
    echo "Zipping files: $file1, $file2, $file3"
    # Zip the files with the same ID
    zip "poet_$id.zip" "$file1" "$file2" "$file3"
done