package benchmark;

import code.HammingCode;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import math.BigInt;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 60, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1)
public class HammingCodeBenchmark {

    private final String message = "100100110110";

    private final BigInteger messageBigInteger = new BigInteger("100100110110", 2);

    private final long messageLong = Long.parseLong("100100110110", 2);
//    private final BigInt messageBigInt = new BigInt(Long.parseLong("100100110110"));

    @Benchmark
    public String encodeString() {
        return HammingCode.encode(message, true);
    }

    @Benchmark
    public BigInteger encodeBigInteger() {
        return HammingCode.encode(messageBigInteger, true, 12);
    }

    @Benchmark
    public void encodeBigInt() {
        HammingCode.encode(new BigInt(messageLong), true, 12);
    }
}
