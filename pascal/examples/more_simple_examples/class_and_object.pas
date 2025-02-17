program x (x);

type
    TPerson = class
    public
        class procedure PrintMessage;
    end;

class procedure TPerson.PrintMessage;
begin
    WriteLn('Static method called');
end;

begin
    TPerson.PrintMessage;
end.