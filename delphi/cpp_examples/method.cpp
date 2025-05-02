extern "C" void print_i32(int x); // to be provided by the WASM host

class ClassDeclaration {
public:
    void Method() {
        print_i32(78);
    }
};

extern "C" void run() {
    ClassDeclaration classInit;
    classInit.Method();
}
