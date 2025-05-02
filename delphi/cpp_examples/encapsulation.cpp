extern "C" void print_i32(int x); // Provided by WASM host

class ClassDeclaration {
private:
    int SecretValue;

public:
    ClassDeclaration() {
        SecretValue = 56;
    }

    void ShowSecret() {
        print_i32(SecretValue);
    }
};

extern "C" void run() {
    ClassDeclaration classInit;
    classInit.ShowSecret();
}
