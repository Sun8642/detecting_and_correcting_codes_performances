package benchmark;

import java.math.BigInteger;
import java.util.Random;
import java.util.SplittableRandom;
import math.BigInt;
import org.junit.jupiter.api.Test;

public class BigIntegerPerformance {

    private static final Random RANDOM = new Random();

    @Test
    public void xor() {
        BigInteger src = new BigInteger(10, RANDOM);
        BigInteger mask = new BigInteger(10, RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 1000000; i++) {
            src.xor(mask);
        }

        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            src.xor(mask);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("Time for xor 10 bits: " + (l2 - l1));

        src = new BigInteger(100, RANDOM);
        mask = new BigInteger(100, RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 1000000; i++) {
            src.xor(mask);
        }

        long l3 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            src.xor(mask);
        }
        long l4 = System.currentTimeMillis();
        System.out.println("Time for xor 100 bits: " + (l4 - l3));

        src = new BigInteger(1000, RANDOM);
        mask = new BigInteger(1000, RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 1000000; i++) {
            src.xor(mask);
        }

        long l5 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            src.xor(mask);
        }
        long l6 = System.currentTimeMillis();
        System.out.println("Time for xor 1000 bits: " + (l6 - l5));

        src = new BigInteger(10000, RANDOM);
        mask = new BigInteger(10000, RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 1000000; i++) {
            src.xor(mask);
        }

        long l7 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            src.xor(mask);
        }
        long l8 = System.currentTimeMillis();
        System.out.println("Time for xor 10000 bits: " + (l8 - l7));

        src = new BigInteger(100000, RANDOM);
        mask = new BigInteger(100000, RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 1000000; i++) {
            src.xor(mask);
        }

        long l9 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            src.xor(mask);
        }
        long l10 = System.currentTimeMillis();
        System.out.println("Time for xor 100000 bits: " + (l10 - l9));
    }
}
