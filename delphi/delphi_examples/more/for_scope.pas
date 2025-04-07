program x (x);

var
    globalValue: Integer;
    localValue: Integer;
    i: Integer;
begin
    globalValue := 5;

    for i := 1 to globalValue do
    begin
        localValue := i * 2;
        WriteLn(globalValue);
        WriteLn(localValue);
    end;
end.