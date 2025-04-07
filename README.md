# Delphi Interpreter (Extended)

## Group 3, Project 2
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

### Generate Java Code from the Delphi Grammar

Generate the parser and visitor using ANTLR4:
```sh
antlr -Dlanguage=Java -visitor delphi.g4
```

---

## Running the Interpreter

### Option 1: Manually Compile and Run

#### Compile Java with the ANTLR JAR dependency:
```sh
javac -cp ".:antlr-4.13.2-complete.jar" delphi*.java
```

#### Run a Delphi test case (e.g., `for_scope.pas`):
```sh
java -cp ".:antlr-4.13.2-complete.jar" delphiCustomInterpreter ./delphi_examples/for_scope.pas
```

---

### Option 2: Use the Run Script

#### Make the script executable:
```sh
chmod +x run.sh
```

#### Run the script with a test case:
```sh
./run.sh ./delphi_examples/for_scope.pas
```

---

## Running All Test Cases

To run all predefined test cases at once, use `run_all.sh`. Ensure you are in the `delphi/` directory:

```sh
cd ./delphi/
chmod +x run_all.sh
./run_all.sh
```

This script will compile the interpreter and execute all `.pas` files in the `./delphi_examples/` directory automatically.

---

## Output Explanation

When running the interpreter, the output will indicate which `.pas` file is being processed, save the generated AST tree, and print an integer value demonstrating the corresponding functionality. Example output:

```
Running interpreter for ./delphi_examples/constructor.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_constructor.txt
12
Running interpreter for ./delphi_examples/destructor.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_destructor.txt
34
Running interpreter for ./delphi_examples/encapsulation.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_encapsulation.txt
56
Running interpreter for ./delphi_examples/method.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_method.txt
78
Running interpreter for ./delphi_examples/break.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_break.txt
100
Running interpreter for ./delphi_examples/continue.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_continue.txt
400
400
400
400
400
Running interpreter for ./delphi_examples/for_scope.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_for_scope.txt
5
1
5
2
5
3
5
4
5
5
Running interpreter for ./delphi_examples/for.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_for.txt
600
600
600
600
600
Running interpreter for ./delphi_examples/function_scope.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_function_scope.txt
800
700
0
Running interpreter for ./delphi_examples/function.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_function.txt
402
Running interpreter for ./delphi_examples/method_scope.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_method_scope.txt
1000
900
Running interpreter for ./delphi_examples/while_scope.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_while_scope.txt
300
400
Running interpreter for ./delphi_examples/while.pas
AST tree saved to /Users/Kasinets/School/Sp25_Dobra/project2/project2/delphi/./ast/AST_Tree_while.txt
101
```

The AST tree serves as proof that the grammar is correctly parsed. The printed integer confirms that the expected functionality (e.g., constructor, destructor, encapsulation, or method) in the `.pas` file is executed correctly by the interpreter.
To better understand how these files are parsed, check the `.pas` input files in the `./delphi_examples/` directory.
