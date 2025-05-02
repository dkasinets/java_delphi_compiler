%ClassDeclaration = type { i8 }

declare void @print_i32(i32) #1

define void @run() {
entry:
  %obj = alloca %ClassDeclaration
  call void @ClassDeclaration_Destroy(ptr %obj)
  ret void
}

define void @ClassDeclaration_Destroy(ptr %this) {
entry:
  call void @print_i32(i32 34)
  ret void
}

attributes #1 = { "wasm-import-module"="env" "wasm-import-name"="print_i32" }
