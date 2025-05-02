extern "C" void print_i32(int x); // assumed to be provided by WASM host

extern "C" void run() {
    int number = 5;
    for (int i = 1; i <= number; ++i) {
        print_i32(600);
    }
}
