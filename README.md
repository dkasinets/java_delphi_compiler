# Simple Delphi Interpreter

## Group 3
**Members:**
- Detravious Jamari Brinkley
- Dzmitry Kasinets

## Setup Instructions

### Install Dependencies
Ensure you have the required dependencies installed:

#### Install ANTLR4 (Version 4.13.2)
```sh
brew install antlr
```

#### Install Java (Version 17.0.14)
```sh
brew install openjdk@17
```

### Setup ANTLR4 in the Delphi Directory
Navigate to the `delphi/` directory:
```sh
cd ./delphi/
```

Download the ANTLR JAR dependency (Version 4.13.2) if Java cannot find it:
```sh
curl -O https://www.antlr.org/download/antlr-4.13.2-complete.jar
```

### Generate Java Code from the Delphi Grammar
Generate the parser and lexer using ANTLR4:
```sh
antlr -Dlanguage=Java -visitor delphi.g4
```

## Running the Interpreter

### Option 1: Manually Compile and Run
#### Compile Java with the ANTLR JAR dependency:
```sh
javac -cp ".:antlr-4.13.2-complete.jar" delphi*.java
```

#### Run a Delphi test case (e.g., `constructor.pas`):
```sh
java -cp ".:antlr-4.13.2-complete.jar" delphiCustomInterpreter ./delphi_examples/constructor.pas
```

### Option 2: Use the Run Script
#### Make the script executable:
```sh
chmod +x run.sh
```

#### Run the script with a test case:
```sh
./run.sh ./delphi_examples/constructor.pas
```

## Running All Test Cases
To run all predefined test cases at once, use `run_all.sh`. Ensure you are in the `delphi/` directory:

```sh
cd ./delphi/
chmod +x run_all.sh
./run_all.sh
```

This script will compile the interpreter and execute all `.pas` files in the `./delphi_examples/` directory automatically.

## Output Explanation
When running the interpreter, the output will indicate which `.pas` file is being processed, save the generated AST tree, and print an integer value demonstrating the corresponding functionality. Example output:

```
Running interpreter for ./delphi_examples/constructor.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project1/delphi/./ast/AST_Tree_constructor.txt
12
Running interpreter for ./delphi_examples/destructor.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project1/delphi/./ast/AST_Tree_destructor.txt
34
Running interpreter for ./delphi_examples/encapsulation.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project1/delphi/./ast/AST_Tree_encapsulation.txt
56
Running interpreter for ./delphi_examples/method.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project1/delphi/./ast/AST_Tree_method.txt
78
```

The AST tree serves as proof that the grammar is correctly parsed. The printed integer confirms that the expected functionality (e.g., constructor, destructor, encapsulation, or method) in the `.pas` file is executed correctly by the interpreter. To better understand how these files are parsed, it is a good idea to look at the input files in `./delphi_examples/`.
