package benchmark;

import math.BigInt;
import org.openjdk.jmh.annotations.*;
import util.SyntheticDataGenerator;

import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

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
        return SyntheticDataGenerator.getRandomWord(12);
    }

    @Benchmark
    public String generateRandom12000BitsString() {
        return SyntheticDataGenerator.getRandomWord(1500 * 8);
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
