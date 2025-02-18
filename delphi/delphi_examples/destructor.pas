program x (x);

type
    ClassDeclaration = class
    public
        destructor Destroy;
    end;

destructor ClassDeclaration.Destroy;
begin
    WriteLn(34);
end;

var
    classInit: ClassDeclaration;
begin
    classInit := ClassDeclaration.Create
    classInit.Destroy;
end.