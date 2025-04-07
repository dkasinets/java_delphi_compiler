program x (x);

type
    ClassDeclaration = class
    public
        procedure Method;
    end;

procedure ClassDeclaration.Method;
begin
    WriteLn(78);
end;

var
    classInit: ClassDeclaration;
begin
    classInit := ClassDeclaration.Create;
    classInit.Method;
end.