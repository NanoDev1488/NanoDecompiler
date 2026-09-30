# Article 085: Profiling and Benchmarking C++ Decompilers: VTune, Tracy, and Throughput Metrics

## 1. Executive Summary & Profiling Methodology
Performance optimization requires measuring concrete hardware performance metrics:
- **Throughput**: Classes decompiled per second (target: > 1,000 classes/sec on modern x86_64).
- **Latency**: P99 decompilation time per method (target: < 5ms).
- **Memory Peak**: Maximum Resident Set Size (RSS) during multi-gigabyte JAR processing.

## 2. Profiling Tools
- **Intel VTune / Linux `perf`**: Identifies CPU branch mispredictions and L1/L2 cache misses in CFG traversals.
- **Tracy Profiler**: Real-time frame-by-frame visualization of thread pool task queues and pipeline phases.

## 3. NanoDecompiler C++ Engine Integration
In `resources/engine_cpp/src/stats_json.cpp`:
- Collects fine-grained execution metrics (bytecode size, method counts, elapsed milliseconds).

## 4. References
- Gregg, B. *Systems Performance: Enterprise and the Cloud (2nd Edition)*. Addison-Wesley, 2020.
