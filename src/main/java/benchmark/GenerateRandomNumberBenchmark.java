package benchmark;

import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;
import math.BigInt;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import util.SyntheticDataGenerator;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 60, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1)
public class GenerateRandomNumberBenchmark {

    private final SplittableRandom splittableRandom = new SplittableRandom();

    @Benchmark
    public String generateRandom12BitsString() {
        return SyntheticDataGenerator.getRandomSplittableWord(12);
    }

    @Benchmark
    public String generateRandom12000BitsString() {
        return SyntheticDataGenerator.getRandomSplittableWord(1500 * 8);
    }

    @Benchmark
    public BigInt generateRandom12BitsBigInt() {
        return new BigInt(12, splittableRandom);
    }

    @Benchmark
    public BigInt generateRandom12000BitsBigInt() {
        return new BigInt(1500 * 8, splittableRandom);
    }
}
