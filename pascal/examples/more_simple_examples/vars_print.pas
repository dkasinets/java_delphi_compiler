program x (x);

var
  str1: string;
  str2: string;
  str3: string;
  num1, num2, num3: integer;

begin
  str1 := 'File 1:';
  str2 := 'File 2:';
  str3 := 'File 3:';
  num1 := 55;
  num2 := 660;
  num3 := 4500;

  writeln(str1, ' ', num1, ', ', num2, ', ', num3);
  writeln('Hello, World!');
  writeln(str2, ' ', num1, ', ', num2, ', ', num3);
  writeln('This is a test.');
  write('Numbers: '); 
  write(str3, ' ', num1, ', ', num2, ', ', num3);
  writeln('.');
end.
