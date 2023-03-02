package benchmark;

import code.CyclicRedundancyCode;
import code.InternetChecksum;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class InternetChecksumPerformance {

    @Test
    public void testPerformanceInternetChecksum() {
        String message = "101011011100101010101101110010101010110111001010";
        BigInteger messageBigInteger = new BigInteger(message, 2);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(message);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("temps InternetChecksum string: " + (l1 - l));
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(messageBigInteger);
        }
        System.out.println("temps InternetChecksum biginteger: " + (System.currentTimeMillis() - l1));
    }
}
