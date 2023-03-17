package benchmark;

import code.CyclicRedundancyCode;
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
import util.SyntheticDataGenerator;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 60, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1)
public class CyclicRedundancyCheckBenchmark {

    private final String message = "100100110110";
    private final String generatorPolynomial = "1001";

    private final String bigMsg = SyntheticDataGenerator.getRandomSplittableWord(1500 * 8);
    private final String crc32 = "100000100110000010001110110110111";

    private final BigInteger messageBigInteger = new BigInteger("100100110110", 2);
    private final BigInteger generatorPolynomialBigInteger = new BigInteger("1001", 2);

    private final BigInteger bigMsgBigInteger = SyntheticDataGenerator.getBigIntegerRandomWord(1500 * 8);
    private final BigInteger crc32BigInteger = new BigInteger(crc32, 2);

    private final long messageLong = Long.parseLong("100100110110", 2);
    private final long generatorPolynomialLong = Long.parseLong("1001", 2);

    private final BigInt bigMsgBigInt = BigInt.from(bigMsgBigInteger);
    private final BigInt crc32BigInt = new BigInt(Long.parseLong("100000100110000010001110110110111", 2));
//    private final BigInt messageBigInt = new BigInt(Long.parseLong("100100110110"));
//    private final BigInt generatorPolynomialBigInt = new BigInt(Long.parseLong("1001"));

    @Benchmark
    public String encodeString() {
        return CyclicRedundancyCode.encode(message, generatorPolynomial);
    }

    @Benchmark
    public String encodeBigMessageString() {
        return CyclicRedundancyCode.encode(bigMsg, crc32);
    }

    @Benchmark
    public BigInteger encodeBigInteger() {
        return CyclicRedundancyCode.encode(messageBigInteger, generatorPolynomialBigInteger);
    }

    @Benchmark
    public void encodeBigMessageBigInteger() {
        CyclicRedundancyCode.encode(bigMsgBigInteger, crc32BigInteger);
    }

    @Benchmark
    public void encodeBigInt() {
        CyclicRedundancyCode.encode(new BigInt(messageLong), new BigInt(generatorPolynomialLong));
    }

    @Benchmark
    public void encodeBigMessageBigInt() {
        CyclicRedundancyCode.encode(new BigInt(bigMsgBigInt), new BigInt(crc32BigInt));
    }
}
