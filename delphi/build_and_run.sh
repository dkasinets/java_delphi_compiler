#!/bin/bash

# Exit immediately on error
set -e

# Initialize variables
LL_PATH=""
O_PATH=""
WASM_PATH=""
LLC="/usr/local/opt/llvm/bin/llc"
WASM_LD="./wasi-sdk/bin/wasm-ld"

# Parse arguments
while [[ $# -gt 0 ]]; do
    key="$1"
    case $key in
        --ll)
        LL_PATH="$2"
        shift 2
        ;;
        --o)
        O_PATH="$2"
        shift 2
        ;;
        --wasm)
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
        echo "  $0 --ll path.ll --o path.o --wasm path.wasm"
        echo "     [--llc path_to_llc] [--wasm-ld path_to_wasm-ld]"
        exit 1
        ;;
    esac
done

# Validate required inputs
if [[ -z "$LL_PATH" || -z "$O_PATH" || -z "$WASM_PATH" ]]; then
    echo "Error: --ll, --o, and --wasm arguments are required"
    echo "Usage:"
    echo "  $0 --ll path.ll --o path.o --wasm path.wasm"
    exit 1
fi

echo "Compiling $LL_PATH to $O_PATH..."
"$LLC" -march=wasm32 -filetype=obj -o "$O_PATH" "$LL_PATH"

echo "Linking $O_PATH to $WASM_PATH..."
"$WASM_LD" --no-entry --export-all -o "$WASM_PATH" "$O_PATH"

if [ -f run.js ]; then
    echo "Running with Node.js..."
    node run.js "$WASM_PATH"
else
    echo "run.js not found. Skipping Node.js execution."
fi
