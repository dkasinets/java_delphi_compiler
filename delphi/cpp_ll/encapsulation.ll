%ClassDeclaration = type { i32 }

declare void @print_i32(i32) #1

define void @run() {
entry:
  %obj = alloca %ClassDeclaration
  call void @ClassDeclaration_Create(ptr %obj)
  call void @ClassDeclaration_ShowSecret(ptr %obj)
  ret void
}

define void @ClassDeclaration_Create(ptr %this) {
entry:
  %field = getelementptr inbounds %ClassDeclaration, ptr %this, i32 0, i32 0
  store i32 56, ptr %field
  ret void
}

define void @ClassDeclaration_ShowSecret(ptr %this) {
entry:
  %field = getelementptr inbounds %ClassDeclaration, ptr %this, i32 0, i32 0
  %val = load i32, ptr %field
  call void @print_i32(i32 %val)
  ret void
}

attributes #1 = { "wasm-import-module"="env" "wasm-import-name"="print_i32" }
