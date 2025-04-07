program x (x);

function GetValue: Integer;
begin
    Result := 42;
end;

var
    result: Integer;
begin
    result := GetValue;
    WriteLn(result);
end.