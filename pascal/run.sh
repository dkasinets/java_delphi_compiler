#!/bin/bash

# Check if the user provided a file path
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <path_to_pascal_file>"
    exit 1
fi

PASCAL_FILE=$1

# Compile the Pascal interpreter
javac -cp ".:antlr-4.13.2-complete.jar" pascal*.java

# Run the Pascal interpreter with the provided file path
java -cp ".:antlr-4.13.2-complete.jar" pascalCustomInterpreter "$PASCAL_FILE"
