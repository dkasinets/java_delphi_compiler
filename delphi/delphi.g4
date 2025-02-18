grammar delphi;

program: 'program' IDENT '(' IDENT ')' ';' classDeclaration* constructorImplementation* destructorImplementation* methodImplementation* variableDeclaration* 'begin' statement* 'end' '.';

classDeclaration: 'type' IDENT '=' 'class'
                  ('public' memberDeclaration*)?
                  ('private' memberDeclaration*)?
                  'end' ';';

memberDeclaration: constructorDeclaration
                  | destructorDeclaration
                  | methodDeclaration;

constructorDeclaration: 'constructor' IDENT ';';

destructorDeclaration: 'destructor' IDENT ';';

constructorImplementation: 'constructor' IDENT '.' IDENT ';' 'begin' statement* 'end' ';';

destructorImplementation: 'destructor' IDENT '.' IDENT ';' 'begin' statement* 'end' ';';

methodDeclaration: 'procedure' IDENT ';';

methodImplementation: 'procedure' IDENT '.' IDENT ';' 'begin' statement* 'end' ';';

variableDeclaration: 'var' IDENT ':' IDENT ';';

statement: assignment
         | methodCall
         | writelnCall;

assignment: IDENT ':=' IDENT '.' IDENT ('(' expression? ')')? ';';

methodCall: IDENT '.' IDENT ('(' expression? ')')? (';'|NEWLINE);

writelnCall: 'WriteLn' '(' expression ')' ';';

expression: INTEGER | IDENT;

IDENT: [a-zA-Z_][a-zA-Z_0-9]*;
INTEGER: [0-9]+;
COLON: ':';
SEMI: ';';
NEWLINE: '\r'? '\n' -> skip;
WS: [ \t]+ -> skip;
