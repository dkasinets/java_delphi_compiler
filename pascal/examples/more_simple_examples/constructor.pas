program x (x);

type
    TPerson = class
    private
        FName: string;
    public
        constructor Create(Name: string);
    end;

constructor TPerson.Create(Name: string);
begin
    FName := Name;
    WriteLn('Constructor called for: ', FName);
end;

var
    Person1: TPerson;
begin
    Person1 := TPerson.Create('Alice');
end.
