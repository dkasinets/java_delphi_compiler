const fs = require('fs');

const wasmPath = process.argv[2];
if (!wasmPath) {
  console.error("Usage: node run.js path/to/module.wasm");
  process.exit(1);
}

(async () => {
  const wasmBuffer = fs.readFileSync(wasmPath);
  const { instance } = await WebAssembly.instantiate(wasmBuffer, {
    env: {
      print_i32: (x) => console.log("WASM:", x),
    }
  });
  instance.exports.run();
})();
