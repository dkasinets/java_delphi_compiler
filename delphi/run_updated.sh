#!/bin/bash

# Parse named arguments
while [[ "$#" -gt 0 ]]; do
    case "$1" in
        -input)
            INPUT_PAS="$2"
            shift 2
            ;;
        -output)
            OUTPUT_LL="$2"
            shift 2
            ;;
        *)
            echo "Unknown option: $1"
            echo "Usage: $0 -input <input.pas> -output <output.ll>"
            exit 1
            ;;
    esac
done

# Check required arguments
if [ -z "$INPUT_PAS" ] || [ -z "$OUTPUT_LL" ]; then
    echo "Usage: $0 -input <input.pas> -output <output.ll>"
    exit 1
fi

# Compile all Java files
javac -cp ".:antlr-4.13.2-complete.jar" delphi*.java

# Run the generator
java -cp ".:antlr-4.13.2-complete.jar" delphiCustomGenerator "$INPUT_PAS" "$OUTPUT_LL"
