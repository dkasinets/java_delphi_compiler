program x (x);

type
    TPerson = class
    private
        FName: string;
    public
        constructor Create(Name: string);
        destructor Destroy; override;
        procedure SetName(NewName: string);
        function GetName: string;
    end;

constructor TPerson.Create(Name: string);
begin
    FName := Name;
    WriteLn('Constructor called for: ', FName);
end;

destructor TPerson.Destroy;
begin
    WriteLn('Destructor called for: ', FName);
end;

procedure TPerson.SetName(NewName: string);
begin
    FName := NewName;
    WriteLn('Name updated to: ', FName);
end;

function TPerson.GetName: string;
begin
    Result := FName;
end;

var
    Person: TPerson;
begin
    Person := TPerson.Create('Alice');
    WriteLn('Current name: ', Person.GetName);

    Person.SetName('Bob');
    WriteLn('Updated name: ', Person.GetName);

    Person.Destroy;
end.
