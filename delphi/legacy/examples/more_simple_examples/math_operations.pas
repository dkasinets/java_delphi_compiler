program x (x);

var
  str1, str2, str3: string;
  num1, num2, num3, sum, diff, prod, quot: integer;

begin
  str1 := 'Test 1:';
  str2 := 'Test 2:';
  str3 := 'Test 3:';
  num1 := 55;
  num2 := 66;
  num3 := 45;

  sum := num1 + num2 + num3;
  diff := num3 - num2 - num1;
  prod := num1 * num2;
  quot := num3 div num2;

  writeln(str1, ' ', num1, ', ', num2, ', ', num3);
  writeln(str2, ' ', num1, ', ', num2, ', ', num3);
  write(str3, ' ', num1, ', ', num2, ', ', num3);
  writeln('.');

  writeln('Sum: ', sum);
  writeln('Difference: ', diff);
  writeln('Product: ', prod);
  writeln('Quotient: ', quot);
end.
