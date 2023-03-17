package benchmark;

import org.junit.jupiter.api.Test;
import util.SyntheticDataGenerator;

public class SyntheticDataGeneratorPerformance {

    @Test
    public void getRandomWord() {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            SyntheticDataGenerator.getRandomWord(16);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("Time for getRandomWord 16 bits: " + (l2 - l1));
        for (int i = 0; i < 10000000; i++) {
            SyntheticDataGenerator.getRandomWord2(16);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("Time for getRandomWord2 16 bits: " + (l3 - l2));
    }

    @Test
    public void getRandomSplittableWord() {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            SyntheticDataGenerator.getRandomSplittableWord(16);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("Time for getRandomSplittableWord 16 bits: " + (l2 - l1));
    }

    @Test
    public void getRandomBigInteger() {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            SyntheticDataGenerator.getBigIntegerRandomWord(16);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("Time for getRandomBigInteger 16 bits: " + (l2 - l1));
    }

    @Test
    public void getBigIntRandomWord() {
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            SyntheticDataGenerator.getBigIntRandomWord(16);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("Time for getBigIntRandomWord 16 bits: " + (l2 - l1));
    }
}
