

## Setting up a simple Interpreter (for Pascal):
<br>

### Generate ANTLR4 Java Code for Pascal
#### Get ANTLR4 (Version 4.13.2):
``brew install antlr``
#### Get Java (Version 17.0.14):
``brew install openjdk@17``
#### Go to downloaded pascal directory (Source: https://github.com/antlr/grammars-v4/tree/master/pascal)
``cd ./pascal/``
#### Get ANTLR JAR dependency (Version 4.13.2) — in case java can't find ANTLR4 in the system
``curl -O https://www.antlr.org/download/antlr-4.13.2-complete.jar`` 
#### Generate Java files (i.e., generate PARSER and LEXER from the grammar using ANTLR4)
``antlr -Dlanguage=Java -visitor -no-listener pascal/pascal.g4``
#### Compile Java with a JAR dependency 
``javac -cp ".:antlr-4.13.2-complete.jar" pascal*.java``
#### Run a simple Pascal test case (947.pas)
``java -cp ".:antlr-4.13.2-complete.jar" pascalCustomInterpreter ./examples/947.pas``