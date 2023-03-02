package code;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import util.SyntheticDataGenerator;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 200, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1)
@BenchmarkMode(org.openjdk.jmh.annotations.Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(org.openjdk.jmh.annotations.Scope.Benchmark)
public class CodeBenchmark {

    String messageTest = SyntheticDataGenerator.getRandomWord(11);
    BigInteger messageTest2 = SyntheticDataGenerator.getBigIntegerRandomWord(11);

    @Test
    public void t() throws RunnerException {
        Options opt = new OptionsBuilder().include(CodeBenchmark.class.getSimpleName()).build();

        new Runner(opt).run();
    }

    @Benchmark
    public void y() {
        HammingCode.encode(messageTest, true);
    }

    @Benchmark
    public void y2() {
        HammingCode.encode(messageTest2, true, 11);
    }
}
