declare void @print_i32(i32) #1

define i32 @GetValue() {
entry:
  ret i32 402
}

define void @run() {
entry:
  %val = call i32 @GetValue()
  call void @print_i32(i32 %val)
  ret void
}

attributes #1 = { "wasm-import-module"="env" "wasm-import-name"="print_i32" }

