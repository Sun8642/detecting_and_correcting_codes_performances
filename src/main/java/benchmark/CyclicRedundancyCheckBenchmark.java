package benchmark;

import code.CyclicRedundancyCode;
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

import java.math.BigInteger;
import java.util.SplittableRandom;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 60, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1)
public class CyclicRedundancyCheckBenchmark {

    private final SplittableRandom splittableRandom = new SplittableRandom();

    private final String message = "100100110110";
    private final String generatorPolynomial = "1001";

    private final String bigMsg = SyntheticDataGenerator.getRandomWord(1500 * 8);
    private final String crc32 = "100000100110000010001110110110111";

    private final StringBuilder messageStringBuilder = new StringBuilder(message);
    private final StringBuilder generatorPolynomialStringBuilder = new StringBuilder(generatorPolynomial);

    private final StringBuilder bigMsgStringBuilder = new StringBuilder(bigMsg);
    private final StringBuilder crc32StringBuilder = new StringBuilder(crc32);

    private final BigInteger messageBigInteger = new BigInteger("100100110110", 2);
    private final BigInteger generatorPolynomialBigInteger = new BigInteger("1001", 2);

    private final BigInteger bigMsgBigInteger = SyntheticDataGenerator.getBigIntegerRandomWord(1500 * 8);
    private final BigInteger crc32BigInteger = new BigInteger(crc32, 2);

    private final long messageLong = Long.parseLong("100100110110", 2);
    private final long generatorPolynomialLong = Long.parseLong("1001", 2);

    private final BigInt bigMsgBigInt = BigInt.from(bigMsgBigInteger);
    private final BigInt crc32BigInt = new BigInt(Long.parseLong("100000100110000010001110110110111", 2));

    @Benchmark
    public void encodeString() {
        CyclicRedundancyCode.encode(messageStringBuilder, generatorPolynomialStringBuilder);
    }

    @Benchmark
    public void encodeRandom12BitsString() {
        CyclicRedundancyCode.encode(SyntheticDataGenerator.getRandomStringBuilderWord(12), generatorPolynomialStringBuilder);
    }

    @Benchmark
    public void encodeBigMessageString() {
        CyclicRedundancyCode.encode(bigMsgStringBuilder, crc32StringBuilder);
    }

    @Benchmark
    public void encodeRandom12000BitsString() {
        CyclicRedundancyCode.encode(SyntheticDataGenerator.getRandomStringBuilderWord(12000), crc32StringBuilder);
    }

    @Benchmark
    public BigInteger encodeBigInteger() {
        return CyclicRedundancyCode.encode(messageBigInteger, generatorPolynomialBigInteger);
    }

    @Benchmark
    public BigInteger encodeBigMessageBigInteger() {
        return CyclicRedundancyCode.encode(bigMsgBigInteger, crc32BigInteger);
    }

    @Benchmark
    public void encodeBigInt() {
        CyclicRedundancyCode.encode(new BigInt(messageLong), new BigInt(generatorPolynomialLong));
    }

    @Benchmark
    public void encodeRandom12BitsBigInt() {
        CyclicRedundancyCode.encode(new BigInt(12, splittableRandom), new BigInt(generatorPolynomialLong));
    }

    @Benchmark
    public void encodeBigMessageBigInt() {
        CyclicRedundancyCode.encode(new BigInt(bigMsgBigInt), new BigInt(crc32BigInt));
    }

    @Benchmark
    public void encodeRandom12000BitsBigInt() {
        CyclicRedundancyCode.encode(new BigInt(12000, splittableRandom), new BigInt(crc32BigInt));
    }
}
