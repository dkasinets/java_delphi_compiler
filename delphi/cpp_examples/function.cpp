extern "C" void print_i32(int x); // Provided by WASM host

int GetValue() {
    return 402;
}

extern "C" void run() {
    int result = GetValue();
    print_i32(result);
}
