

# Setting up a simple Interpreter (for Delphi):
<br>

## Generate ANTLR4 Java Code for Delphi
### Get ANTLR4 (Version 4.13.2):
``brew install antlr``
### Get Java (Version 17.0.14):
``brew install openjdk@17``
### Go to delphi directory
``cd ./delphi/``
### Get ANTLR JAR dependency (Version 4.13.2) — in case Java environment can't find ANTLR4 in the system
``curl -O https://www.antlr.org/download/antlr-4.13.2-complete.jar`` 
### Generate Java files (i.e., generate PARSER and LEXER from the grammar using ANTLR4)
``antlr -Dlanguage=Java -visitor delphi.g4``
### Running code: 
### Option 1: 
### Compile Java with a JAR dependency 
``javac -cp ".:antlr-4.13.2-complete.jar" delphi*.java``
### Run a Delphi test case (e.g., 947.pas)
``java -cp ".:antlr-4.13.2-complete.jar" delphiCustomInterpreter ./delphi_examples/constructor.pas``
### Option 2: 
### Make it executable
``chmod +x run.sh``
### Run .sh file (that compiles and runs Java code) with a Delphi test case (as argument)
``./run.sh ./delphi_examples/constructor.pas``