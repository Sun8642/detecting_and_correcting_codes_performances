package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import math.BigInt;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;
import java.util.SplittableRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SyntheticDataGenerator {

    private static final Random RANDOM = new Random();
    private static final SplittableRandom SPLITTABLE_RANDOM = new SplittableRandom();

    public static String getRandomWord(int numberOfBits) {
        String s = new BigInteger(numberOfBits, RANDOM).toString(2);
        return s.length() == numberOfBits ? s : "0".repeat(numberOfBits - s.length()) + s;
    }

    public static StringBuilder getRandomStringBuilderWord(int numberOfBits) {
        StringBuilder s = new StringBuilder(new BigInteger(numberOfBits, RANDOM).toString(2));
        if (s.length() != numberOfBits) {
            s.insert(0, "0".repeat(numberOfBits - s.length()));
        }
        return s;
    }

    public static BigInteger getBigIntegerRandomWord(int numberOfBits) {
        return new BigInteger(numberOfBits, RANDOM);
    }

    public static BigInt getBigIntRandomWord(int numberOfBits) {
        return new BigInt(numberOfBits, SPLITTABLE_RANDOM);
    }

    public static long getLongRandomWord(int numberOfBits) {
        return (long) Math.floor(Math.random() * (1 << numberOfBits));
    }

    public static BitSet getBitsetRandomWord(int numberOfBits) {
        return BitSet.valueOf(new long[]{(long) Math.floor(Math.random() * (1 << numberOfBits))});
    }

    public static String corruptWord(String message, double p) {
        StringBuilder stringBuilder = new StringBuilder(message);
        for (int i = 0; i < message.length(); i++) {
            if (Math.random() <= p) {
                stringBuilder.replace(i, i + 1, message.charAt(i) == '0' ? "1" : "0");
            }
        }
        return stringBuilder.toString();
    }

    public static BigInteger corruptWord(BigInteger message, int messageLength, double p) {
        for (int i = 0; i < messageLength; i++) {
            if (Math.random() <= p) {
                message = message.flipBit(i);
            }
        }
        return message;
    }

    public static String corruptWord(String message, double p, boolean isBurstError, int burstErrorLength) {
        StringBuilder stringBuilder = new StringBuilder(message);
        for (int i = 0; i < message.length(); i++) {
            if (Math.random() <= p) {
                if (isBurstError) {
                    stringBuilder.replace(i, Math.min(i + burstErrorLength, message.length()), message.charAt(i) == '0' ? "1" : "0");
                    i += (burstErrorLength - 1);
                } else {
                    stringBuilder.replace(i, i + 1, message.charAt(i) == '0' ? "1" : "0");
                }
            }
        }
        return stringBuilder.toString();
    }
}
