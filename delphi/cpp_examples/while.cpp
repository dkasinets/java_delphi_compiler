extern "C" void print_i32(int x); // provided by the WASM host

extern "C" void run() {
    int number = 0;
    while (number == 0) {
        print_i32(101);
        number = 1;
    }
}
