package benchmark;

import code.InternetChecksum;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import math.BigInt;
import util.SyntheticDataGenerator;

public class InternetChecksumPerformance {

    @Test
    public void testPerformanceInternetChecksum() {
        String message = "101011011100101010101101110010101010110111001010";
        BigInteger messageBigInteger = new BigInteger(message, 2);
        long val = Long.parseLong(message, 2);
        long methodStartTime;


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(message);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(message);
        }
        System.out.println("temps InternetChecksum string: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(messageBigInteger);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(messageBigInteger);
        }
        System.out.println("temps InternetChecksum biginteger: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(new BigInt(val));
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(new BigInt(val));
        }
        System.out.println("temps InternetChecksum bigint: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(val);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(val);
        }
        System.out.println("temps InternetChecksum long: " + (System.currentTimeMillis() - methodStartTime) + "ms");
    }

    @Test
    public void testPerformanceInternetChecksum_bigMessage() {
        BigInteger messageBigInteger = SyntheticDataGenerator.getBigIntegerRandomWord(1500 * 8);
        String message = messageBigInteger.toString(2);
        BigInt messageBigInt = BigInt.from(messageBigInteger);
        long methodStartTime;


        //Warming up the VM
        for (int i = 0; i < 10000; i++) {
            InternetChecksum.encode(message);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            InternetChecksum.encode(message);
        }
        System.out.println("temps InternetChecksum string: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 10000; i++) {
            InternetChecksum.encode(messageBigInteger);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            InternetChecksum.encode(messageBigInteger);
        }
        System.out.println("temps InternetChecksum biginteger: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 10000; i++) {
            InternetChecksum.encode(new BigInt(messageBigInt));
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            InternetChecksum.encode(new BigInt(messageBigInt));
        }
        System.out.println("temps InternetChecksum bigint: " + (System.currentTimeMillis() - methodStartTime) + "ms");
    }
}
