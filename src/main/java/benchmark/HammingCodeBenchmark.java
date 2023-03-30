package benchmark;

import code.HammingCode;
import math.BigInt;
import org.openjdk.jmh.annotations.*;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 60, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1)
public class HammingCodeBenchmark {

    private final String message = "100100110110";

    private final BigInteger messageBigInteger = new BigInteger(message, 2);

    private final long messageLong = Long.parseLong(message, 2);

    @Benchmark
    public void encodeString() {
        HammingCode.encode(new StringBuilder(message), true);
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
