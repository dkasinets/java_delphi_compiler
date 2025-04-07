program x (x);

var
    globalValue: Integer;
    localValue: Integer;
    counter: Integer;
begin
    globalValue := 300;
    counter := 0;

    while counter = 0 do
    begin
        localValue := 400;
        WriteLn(globalValue);
        WriteLn(localValue);
        counter := 1;
    end;
end.