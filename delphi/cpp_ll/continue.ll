declare void @print_i32(i32) #0

define void @run() {
entry:
  %number = alloca i32
  %i = alloca i32
  store i32 5, ptr %number
  store i32 1, ptr %i
  br label %loop

loop:
  %iv = load i32, ptr %i
  %n = load i32, ptr %number
  %cond = icmp sle i32 %iv, %n
  br i1 %cond, label %body, label %exit

body:
  call void @print_i32(i32 400)
  %next = add i32 %iv, 1
  store i32 %next, ptr %i
  br label %loop

exit:
  ret void
}

attributes #0 = { "wasm-import-module"="env" "wasm-import-name"="print_i32" }
