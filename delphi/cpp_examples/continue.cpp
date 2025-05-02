void print_i32(int x); // imported

extern "C" void run() {
    int number = 5;
    for (int i = 1; i <= number; ++i) {
        print_i32(400);
        continue;
    }
}
