package benchmark;

import java.util.Random;
import java.util.SplittableRandom;
import math.BigInt;
import org.junit.jupiter.api.Test;

public class BigIntPerformance {

    private static final Random RANDOM = new Random();
    private static final SplittableRandom SPLITTABLE_RANDOM = new SplittableRandom();

    @Test
    public void getRandomWord16() {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            new BigInt(16, RANDOM);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("Time for random 16 bits: " + (l2 - l1));
        for (int i = 0; i < 1000000; i++) {
            new BigInt(16, SPLITTABLE_RANDOM);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("Time for splittableRandom 16 bits: " + (l3 - l2));
    }

    @Test
    public void getRandomWord32() {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            new BigInt(32, RANDOM);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("Time for random 32 bits: " + (l2 - l1));
        for (int i = 0; i < 1000000; i++) {
            new BigInt(32, SPLITTABLE_RANDOM);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("Time for splittableRandom 32 bits: " + (l3 - l2));
    }

    @Test
    public void getRandomWord64() {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            new BigInt(64, RANDOM);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("Time for random 64 bits: " + (l2 - l1));
        for (int i = 0; i < 1000000; i++) {
            new BigInt(64, SPLITTABLE_RANDOM);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("Time for splittableRandom 64 bits: " + (l3 - l2));
    }

    @Test
    public void getRandomWord2048() {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            new BigInt(2048, RANDOM);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("Time for random 2048 bits: " + (l2 - l1));
        for (int i = 0; i < 1000000; i++) {
            new BigInt(2048, SPLITTABLE_RANDOM);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("Time for splittableRandom 2048 bits: " + (l3 - l2));
    }

    @Test
    public void xor() {
        BigInt src = new BigInt(10, SPLITTABLE_RANDOM);
        BigInt mask = new BigInt(10, SPLITTABLE_RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 1000000; i++) {
            src.xor2(mask);
        }

        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            src.xor2(mask);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("Time for xor 10 bits: " + (l2 - l1));

        src = new BigInt(100, SPLITTABLE_RANDOM);
        mask = new BigInt(100, SPLITTABLE_RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 1000000; i++) {
            src.xor2(mask);
        }

        long l3 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            src.xor2(mask);
        }
        long l4 = System.currentTimeMillis();
        System.out.println("Time for xor 100 bits: " + (l4 - l3));

        src = new BigInt(1000, SPLITTABLE_RANDOM);
        mask = new BigInt(1000, SPLITTABLE_RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 1000000; i++) {
            src.xor2(mask);
        }

        long l5 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            src.xor2(mask);
        }
        long l6 = System.currentTimeMillis();
        System.out.println("Time for xor 1000 bits: " + (l6 - l5));

        src = new BigInt(10000, SPLITTABLE_RANDOM);
        mask = new BigInt(10000, SPLITTABLE_RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 1000000; i++) {
            src.xor2(mask);
        }

        long l7 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            src.xor2(mask);
        }
        long l8 = System.currentTimeMillis();
        System.out.println("Time for xor 10000 bits: " + (l8 - l7));

        src = new BigInt(100000, SPLITTABLE_RANDOM);
        mask = new BigInt(100000, SPLITTABLE_RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 1000000; i++) {
            src.xor2(mask);
        }

        long l9 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            src.xor2(mask);
        }
        long l10 = System.currentTimeMillis();
        System.out.println("Time for xor 100000 bits: " + (l10 - l9));
    }
}
