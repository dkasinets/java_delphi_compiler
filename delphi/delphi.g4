grammar delphi;

program: 'program' IDENT '(' IDENT ')' ';' classDeclaration* constructorImplementation* destructorImplementation* methodImplementation* variableDeclaration* 'begin' statement* 'end' '.';

classDeclaration: 'type' IDENT '=' 'class'
                  (visibilitySection)*
                  'end' ';';

visibilitySection: ('public' | 'private') memberDeclaration*;

memberDeclaration: constructorDeclaration
                  | destructorDeclaration
                  | methodDeclaration
                  | fieldDeclaration;

constructorDeclaration: 'constructor' IDENT ';';

destructorDeclaration: 'destructor' IDENT ';';

constructorImplementation: 'constructor' IDENT '.' IDENT ';' 'begin' statement* 'end' ';';

destructorImplementation: 'destructor' IDENT '.' IDENT ';' 'begin' statement* 'end' ';';

methodDeclaration: 'procedure' IDENT ';';

methodImplementation: 'procedure' IDENT '.' IDENT ';' 'begin' statement* 'end' ';';

fieldDeclaration: IDENT ':' type_ ';';

variableDeclaration: 'var' IDENT ':' type_ ';';

type_: 'Integer' | 'String' | 'Boolean' | IDENT;

statement: variableAssignment
         | assignment
         | methodCall
         | writelnCall
         | variableDeclaration;

variableAssignment: IDENT ':=' expression ';';

assignment: IDENT ':=' expression ';';

methodCall: IDENT '.' IDENT ('(' expression? ')')? (';'|NEWLINE);

writelnCall: 'WriteLn' '(' expression ')' ';';

objectCreation: IDENT '.' IDENT '('? ')'?;

expression: INTEGER | IDENT | objectCreation;

IDENT: [a-zA-Z_][a-zA-Z_0-9]*;
INTEGER: [0-9]+;
COLON: ':';
SEMI: ';';
NEWLINE: '\r'? '\n' -> skip;
WS: [ \t]+ -> skip;
