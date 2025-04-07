program x (x);

var
    globalValue: Integer;

function GetValue: Integer;
var
    localValue: Integer;
begin
    localValue := 700;
    WriteLn(globalValue);
    WriteLn(localValue);
    Result := 0;
end;

var
    result: Integer;
begin
    globalValue := 800;
    result := GetValue;
    WriteLn(result);
end.