%ClassDeclaration = type { i8 }
declare void @print_i32(i32) #0

define void @run() {
entry:
  %classInit = alloca %ClassDeclaration
  call void @ClassDeclaration_Method(ptr %classInit)
exit:
  ret void
}

define void @ClassDeclaration_Method(ptr %this) {
entry:
  call void @print_i32(i32 78)
  ret void
}

attributes #0 = { "wasm-import-module"="env" "wasm-import-name"="print_i32" }

