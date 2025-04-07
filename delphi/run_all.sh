#!/bin/bash

# Define the directory and file list
EXAMPLES_DIR="./delphi_examples"
FILES=(
    "constructor.pas"
    "destructor.pas"
    "encapsulation.pas"
    "method.pas"
    "break.pas"
    "continue.pas"
    "for_scope.pas"
    "for.pas"
    "function_scope.pas"
    "function.pas"
    "method_scope.pas"
    "while_scope.pas"
    "while.pas"
)

# Compile the Delphi interpreter
javac -cp ".:antlr-4.13.2-complete.jar" delphi*.java

# Run the interpreter for each file
for FILE in "${FILES[@]}"; do
    echo "Running interpreter for $EXAMPLES_DIR/$FILE"
    java -cp ".:antlr-4.13.2-complete.jar" delphiCustomInterpreter "$EXAMPLES_DIR/$FILE"
done
