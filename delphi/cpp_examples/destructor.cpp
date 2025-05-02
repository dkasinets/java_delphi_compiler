extern "C" void print_i32(int x); // Provided by WASM host

class ClassDeclaration {
public:
    void Destroy() {
        print_i32(34);
    }
};

extern "C" void run() {
    ClassDeclaration classInit;
    classInit.Destroy();
}
