program x (x);

type
    TPerson = class
    private
        FName: string;
    public
        constructor Create(Name: string);
        procedure SetName(NewName: string);
        function GetName: string;
    end;

constructor TPerson.Create(Name: string);
begin
    FName := Name;
    WriteLn('A new person has been created with name: ', FName);
end;

procedure TPerson.SetName(NewName: string);
begin
    FName := NewName;
    WriteLn('Name has been updated to: ', FName);
end;

function TPerson.GetName: string;
begin
    Result := FName;
end;

var
    Person1: TPerson;
begin
    Person1 := TPerson.Create('Alice');
    WriteLn('Current name: ', Person1.GetName);
    
    Person1.SetName('Bob');
    WriteLn('Updated name: ', Person1.GetName);
end.
