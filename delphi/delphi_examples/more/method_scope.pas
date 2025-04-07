program x (x);

var
    globalValue: Integer;

type
    ClassDeclaration = class
    public
        procedure Method;
    end;

procedure ClassDeclaration.Method;
var
    localValue: Integer;
begin
    localValue := 900;
    WriteLn(globalValue);
    WriteLn(localValue);
end;

var
    classInit: ClassDeclaration;
begin
    globalValue := 1000;
    classInit := ClassDeclaration.Create;
    classInit.Method;
end.