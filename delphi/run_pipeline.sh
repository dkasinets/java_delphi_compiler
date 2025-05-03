#!/bin/bash

set -e

# Defaults
LLC="/usr/local/opt/llvm/bin/llc"
WASM_LD="./wasi-sdk/bin/wasm-ld"

# Parse arguments
while [[ $# -gt 0 ]]; do
    case "$1" in
        -input)
            INPUT_PAS="$2"
            shift 2
            ;;
        -ll)
            OUTPUT_LL="$2"
            shift 2
            ;;
        -o)
            O_PATH="$2"
            shift 2
            ;;
        -wasm)
            WASM_PATH="$2"
            shift 2
            ;;
        --llc)
            LLC="$2"
            shift 2
            ;;
        --wasm-ld)
            WASM_LD="$2"
            shift 2
            ;;
        *)
            echo "Unknown option: $1"
            echo "Usage:"
            echo "  $0 -input file.pas -ll file.ll -o file.o -wasm file.wasm"
            echo "     [--llc path_to_llc] [--wasm-ld path_to_wasm-ld]"
            exit 1
            ;;
    esac
done

# Check required inputs
if [[ -z "$INPUT_PAS" || -z "$OUTPUT_LL" || -z "$O_PATH" || -z "$WASM_PATH" ]]; then
    echo "Error: Missing required arguments"
    echo "Usage:"
    echo "  $0 -input file.pas -ll file.ll -o file.o -wasm file.wasm"
    exit 1
fi

# Compile Java sources
echo "1. Compiling Java sources..."
javac -cp ".:antlr-4.13.2-complete.jar" delphi*.java

# Run Delphi to LLVM IR generator
echo "2. Generating LLVM IR from $INPUT_PAS..."
java -cp ".:antlr-4.13.2-complete.jar" delphiCustomGenerator "$INPUT_PAS" "$OUTPUT_LL"

# Compile LLVM IR to object
echo "3. Compiling $OUTPUT_LL to $O_PATH..."
"$LLC" -march=wasm32 -filetype=obj -o "$O_PATH" "$OUTPUT_LL"

# Link object to WASM
echo "4. Linking $O_PATH to $WASM_PATH..."
"$WASM_LD" --no-entry --export-all -o "$WASM_PATH" "$O_PATH"

# Run via Node.js if run.js exists
if [ -f run.js ]; then
    echo "5. Running with Node.js..."
    node run.js "$WASM_PATH"
else
    echo "run.js not found. Skipping Node.js execution."
fi
