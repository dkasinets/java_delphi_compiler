#!/bin/bash

# Check if the user provided a file path
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <path_to_delphi_file>"
    exit 1
fi

DELPHI_FILE=$1

# Compile the Delphi interpreter
javac -cp ".:antlr-4.13.2-complete.jar" delphi*.java

# Run the Delphi interpreter with the provided file path
java -cp ".:antlr-4.13.2-complete.jar" delphiCustomInterpreter "$DELPHI_FILE"
