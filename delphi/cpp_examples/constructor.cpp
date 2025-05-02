extern "C" void print_i32(int x); // provided by the WASM host

class ClassDeclaration {
public:
    ClassDeclaration() {
        print_i32(12);
    }
};

extern "C" void run() {
    ClassDeclaration classInit;
}
