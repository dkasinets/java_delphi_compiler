program x (x);

type
    TPerson = class
    private
        FName: string;
    public
        constructor Create(Name: string);
        destructor Destroy; override;
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

var
    Person2: TPerson;
begin
    Person2 := TPerson.Create('Bob');
    Person2.Destroy;
end.
