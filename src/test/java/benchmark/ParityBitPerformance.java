package benchmark;

import code.CyclicRedundancyCode;
import code.ParityBitCode;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class ParityBitPerformance {

    @Test
    public void testPerformanceParityBit() {
        String message = "10110010110";
        BigInteger messageBigInteger = new BigInteger(message, 2);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(message);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("temps pbc string: " + (l1 - l));
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(messageBigInteger);
        }
        System.out.println("temps pbc biginteger: " + (System.currentTimeMillis() - l1));
    }
}
