

## Setting up:
<br>

### Generate ANTLR4 Java Code for Pascal
#### Get ANTLR4:
``brew install antlr``
#### Get Java:
``brew install openjdk@17``
#### Go to pascal directory
``cd ./pascal/``
#### Get ANTLR JAR dependency
``curl -O https://www.antlr.org/download/antlr-4.13.2-complete.jar`` 
#### Generate Java files
``antlr -Dlanguage=Java -visitor -no-listener pascal/pascal.g4``
#### Compile Java with a JAR dependency 
``javac -cp ".:antlr-4.13.2-complete.jar" pascal*.java``
#### Run Pascal code
``java -cp ".:antlr-4.13.2-complete.jar" pascalCustomInterpreter ./examples/947.pas``