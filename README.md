# Delphi Compiler into WebAssembly

## Group 3, Final Project
**Members:**
- Detravious Jamari Brinkley
- Dzmitry Kasinets

---

## Setup Instructions

### Install Dependencies

Ensure you have the required dependencies installed:

#### Install ANTLR4 (Version 4.13.2)
```sh
brew install antlr
```

#### Install LLVM (Version 20.1.3)
```sh
brew install llvm
```

#### Install WASMTIME (Version 32.0.0)
```sh
brew install wasmtime
```

#### Install Java (Version 17.0.14)
```sh
brew install openjdk@17
```

---

### Setup ANTLR4 in the Delphi Directory

Navigate to the `delphi/` directory:
```sh
cd ./delphi/
```

Download the ANTLR JAR dependency (Version 4.13.2) if Java cannot find it:
```sh
curl -O https://www.antlr.org/download/antlr-4.13.2-complete.jar
```

---

### WASI SDK Requirement

To link object files into `.wasm` binaries, this project uses the [WASI SDK](https://github.com/WebAssembly/wasi-sdk/releases). You can download the latest release from:

[https://github.com/WebAssembly/wasi-sdk/releases](https://github.com/WebAssembly/wasi-sdk/releases)

After downloading, extract the SDK and update the `WASM_LD` path in your scripts (e.g., `run_pipeline.sh`) to point to:

---

### Generate Java Code from the Delphi Grammar

Generate the parser and visitor using ANTLR4:
```sh
antlr -Dlanguage=Java -visitor delphi.g4
```



## Main Entry Point: `run_pipeline.sh`

This script automates the full pipeline:

1. Compiles Java source files for the interpreter.
2. Parses the input `.pas` file and generates an AST and LLVM IR.
3. Uses `llc` to compile the IR into an object file.
4. Uses `wasm-ld` from the WASI SDK to link the object into a `.wasm` binary.
5. Runs the WebAssembly module using Node.js and prints the output.

---

## Output Explanation

When running the compiler via `run_pipeline.sh`, the output indicates which `.pas` file is being compiled and run. It prints each step of the pipeline, including Java compilation, AST and LLVM IR generation, object and WebAssembly file creation, and finally execution. The final output demonstrates the result of executing the Delphi program compiled to WebAssembly.

Example output:

```
(base) MacBook-Pro:delphi Kasinets$ ./run_pipeline.sh -input ./delphi_examples/break.pas -ll ./delphi_ll/break.ll -o ./delphi_o/break.o -wasm ./delphi_wasm/break.wasm
1. Compiling Java sources...
2. Generating LLVM IR from ./delphi_examples/break.pas...
    AST tree saved to /Users/Kasinets/School/Sp25_Dobra/final_project_LLVM/project3/delphi/./ast/AST_Tree_break.txt
    LLVM IR written to ./delphi_ll/break.ll
3. Compiling ./delphi_ll/break.ll to ./delphi_o/break.o...
4. Linking ./delphi_o/break.o to ./delphi_wasm/break.wasm...
5. Running with Node.js...
WASM: 100

(base) MacBook-Pro:delphi Kasinets$ ./run_pipeline.sh -input ./delphi_examples/continue.pas -ll ./delphi_ll/continue.ll -o ./delphi_o/continue.o -wasm ./delphi_wasm/continue.wasm
1. Compiling Java sources...
2. Generating LLVM IR from ./delphi_examples/continue.pas...
    AST tree saved to /Users/Kasinets/School/Sp25_Dobra/final_project_LLVM/project3/delphi/./ast/AST_Tree_continue.txt
    LLVM IR written to ./delphi_ll/continue.ll
3. Compiling ./delphi_ll/continue.ll to ./delphi_o/continue.o...
4. Linking ./delphi_o/continue.o to ./delphi_wasm/continue.wasm...
5. Running with Node.js...
WASM: 400
WASM: 400
WASM: 400
WASM: 400
WASM: 400

(base) MacBook-Pro:delphi Kasinets$ ./run_pipeline.sh -input ./delphi_examples/for.pas -ll ./delphi_ll/for.ll -o ./delphi_o/for.o -wasm ./delphi_wasm/for.wasm
1. Compiling Java sources...
2. Generating LLVM IR from ./delphi_examples/for.pas...
    AST tree saved to /Users/Kasinets/School/Sp25_Dobra/final_project_LLVM/project3/delphi/./ast/AST_Tree_for.txt
    LLVM IR written to ./delphi_ll/for.ll
3. Compiling ./delphi_ll/for.ll to ./delphi_o/for.o...
4. Linking ./delphi_o/for.o to ./delphi_wasm/for.wasm...
5. Running with Node.js...
WASM: 600
WASM: 600
WASM: 600
WASM: 600
WASM: 600

(base) MacBook-Pro:delphi Kasinets$ ./run_pipeline.sh -input ./delphi_examples/function.pas -ll ./delphi_ll/function.ll -o ./delphi_o/function.o -wasm ./delphi_wasm/function.wasm
1. Compiling Java sources...
2. Generating LLVM IR from ./delphi_examples/function.pas...
    AST tree saved to /Users/Kasinets/School/Sp25_Dobra/final_project_LLVM/project3/delphi/./ast/AST_Tree_function.txt
    LLVM IR written to ./delphi_ll/function.ll
3. Compiling ./delphi_ll/function.ll to ./delphi_o/function.o...
4. Linking ./delphi_o/function.o to ./delphi_wasm/function.wasm...
5. Running with Node.js...
WASM: 402

(base) MacBook-Pro:delphi Kasinets$ ./run_pipeline.sh -input ./delphi_examples/while.pas -ll ./delphi_ll/while.ll -o ./delphi_o/while.o -wasm ./delphi_wasm/while.wasm
1. Compiling Java sources...
2. Generating LLVM IR from ./delphi_examples/while.pas...
    AST tree saved to /Users/Kasinets/School/Sp25_Dobra/final_project_LLVM/project3/delphi/./ast/AST_Tree_while.txt
    LLVM IR written to ./delphi_ll/while.ll
3. Compiling ./delphi_ll/while.ll to ./delphi_o/while.o...
4. Linking ./delphi_o/while.o to ./delphi_wasm/while.wasm...
5. Running with Node.js...
WASM: 101

(base) MacBook-Pro:delphi Kasinets$ ./run_pipeline.sh -input ./delphi_examples/method.pas -ll ./delphi_ll/method.ll -o ./delphi_o/method.o -wasm ./delphi_wasm/method.wasm
1. Compiling Java sources...
2. Generating LLVM IR from ./delphi_examples/method.pas...
    AST tree saved to /Users/Kasinets/School/Sp25_Dobra/final_project_LLVM/project3/delphi/./ast/AST_Tree_method.txt
    LLVM IR written to ./delphi_ll/method.ll
3. Compiling ./delphi_ll/method.ll to ./delphi_o/method.o...
4. Linking ./delphi_o/method.o to ./delphi_wasm/method.wasm...
5. Running with Node.js...
WASM: 78
```

Each `WASM:` output is produced by executing the compiled .wasm file using a Node.js script. The script loads the WebAssembly module, runs its run function, and logs integer outputs via the imported print_i32 function.