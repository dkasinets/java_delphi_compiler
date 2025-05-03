program x (x);

type
    ClassDeclaration = class
    private
        SecretValue: Integer;
    public
        constructor Create;
        procedure ShowSecret;
    end;

constructor ClassDeclaration.Create;
begin
    SecretValue := 56;
end;

procedure ClassDeclaration.ShowSecret;
begin
    WriteLn(SecretValue);
end;

var
    classInit: ClassDeclaration;
begin
    classInit := ClassDeclaration.Create;
    classInit.ShowSecret;
end.