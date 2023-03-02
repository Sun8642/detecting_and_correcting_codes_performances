package benchmark;

import code.CyclicRedundancyCode;
import code.HammingCode;
import java.math.BigInteger;
import java.util.BitSet;
import org.junit.jupiter.api.Test;
import test.BigInt;
import util.SyntheticDataGenerator;

public class CrcPerformance {

    @Test
    public void testPerformanceCRC() {
        String message = "10010101010101010101010";
        String generatorPolynomial = "1000110110010101";
        BigInteger messageBigInteger = new BigInteger(message, 2);
        BigInteger generatorPolynomialBigInteger = new BigInteger(generatorPolynomial, 2);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(message, generatorPolynomial);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("temps CyclicRedundancyCode string: " + (l1 - l));
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(messageBigInteger, generatorPolynomialBigInteger);
        }
        System.out.println("temps CyclicRedundancyCode biginteger: " + (System.currentTimeMillis() - l1));
    }
}
