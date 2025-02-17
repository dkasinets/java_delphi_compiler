program x (x);

type
    ClassDeclaration = class
    public
        constructor Create;
    end;

constructor ClassDeclaration.Create;
begin
    WriteLn(12);
end;

var
    classInit: ClassDeclaration;
begin
    classInit := ClassDeclaration.Create;
end.