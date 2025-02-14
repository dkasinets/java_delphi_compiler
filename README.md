

# Setting up a simple Interpreter (for Pascal & Delphi):
<br>

## Generate ANTLR4 Java Code for Pascal
### Get ANTLR4 (Version 4.13.2):
``brew install antlr``
### Get Java (Version 17.0.14):
``brew install openjdk@17``
### Go to pascal directory ([source](https://github.com/antlr/grammars-v4/tree/master/pascal))
``cd ./pascal/``
### Get ANTLR JAR dependency (Version 4.13.2) — in case Java environment can't find ANTLR4 in the system
``curl -O https://www.antlr.org/download/antlr-4.13.2-complete.jar`` 
### Generate Java files (i.e., generate PARSER and LEXER from the grammar using ANTLR4)
``antlr -Dlanguage=Java -visitor -no-listener pascal.g4``
### Running code: 
### Option 1: 
### Compile Java with a JAR dependency 
``javac -cp ".:antlr-4.13.2-complete.jar" pascal*.java``
### Run a Pascal test case (e.g., 947.pas)
``java -cp ".:antlr-4.13.2-complete.jar" pascalCustomInterpreter ./examples/947.pas``
### Option 2: 
### Make it executable
``chmod +x run.sh``
### Run .sh file (that compiles and runs Java code) with a Pascal test case (as argument)
``./run.sh ./examples/add.pas``