package benchmark;

import code.HammingCode;
import java.math.BigInteger;
import java.util.BitSet;
import org.junit.jupiter.api.Test;
import math.BigInt;
import util.SyntheticDataGenerator;

public class HammingPerformance {

    @Test
    public void testPerformanceEncode() {
        String message = "10110010110";
        BigInteger messageBigInteger = new BigInteger(message, 2);
        long messageLong = Long.parseLong(message, 2);
        BitSet messageBitSet;
        BigInt messageBigInt = new BigInt(messageLong);
        long methodStartTime;


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(message, true);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(message, true);
        }
        System.out.println("temps hamming string: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(messageBigInteger, true, 11);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(messageBigInteger, true, 11);
        }
        System.out.println("temps hamming biginteger: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(messageLong, true, 11);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(messageLong, true, 11);
        }
        System.out.println("temps hamming long: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            messageBitSet = BitSet.valueOf(new long[]{messageLong});
            HammingCode.encode(messageBitSet, true, 11);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            messageBitSet = BitSet.valueOf(new long[]{messageLong});
            HammingCode.encode(messageBitSet, true, 11);
        }
        System.out.println("temps hamming bitset: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(messageBigInt, true, 11);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(messageBigInt, true, 11);
        }
        System.out.println("temps hamming BigInt: " + (System.currentTimeMillis() - methodStartTime) + "ms");
    }

    @Test
    public void testPerformanceGenerateRandomMessageAndEncode() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(SyntheticDataGenerator.getRandomWord(11), true);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("temps hamming string: " + (l1 - l));
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(SyntheticDataGenerator.getBigIntegerRandomWord(11), true, 11);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("temps hamming biginteger: " + (l2 - l1));
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(SyntheticDataGenerator.getLongRandomWord(11), true, 11);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("temps hamming long: " + (l3 - l2));
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(SyntheticDataGenerator.getBitsetRandomWord(11), true, 11);
        }
        long l4 = System.currentTimeMillis();
        System.out.println("temps hamming bitset: " + (l4 - l3));
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(SyntheticDataGenerator.getBigIntRandomWord(11), true, 11);
        }
        long l5 = System.currentTimeMillis();
        System.out.println("temps hamming bigint: " + (l5 - l4));
    }
}
