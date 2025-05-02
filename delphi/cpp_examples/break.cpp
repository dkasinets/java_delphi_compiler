// test.c
void print_i32(int x); // assume this will be provided by the WASM host

void run() {
    int number = 5;
    for (int i = 1; i <= number; i++) {
        print_i32(100);
        break;
    }
}
