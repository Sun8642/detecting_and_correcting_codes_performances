package benchmark;

import java.util.Random;
import java.util.SplittableRandom;
import org.junit.jupiter.api.Test;
import math.BigInt;

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
}
