declare void @print_i32(i32) #0

define void @run() {
entry:
  %number = alloca i32
  store i32 0, ptr %number
  br label %loop

loop:
  %val = load i32, ptr %number
  %cond = icmp eq i32 %val, 0
  br i1 %cond, label %body, label %exit

body:
  call void @print_i32(i32 101)
  store i32 1, ptr %number
  br label %loop
exit:
  ret void
}

attributes #0 = { "wasm-import-module"="env" "wasm-import-name"="print_i32" }

