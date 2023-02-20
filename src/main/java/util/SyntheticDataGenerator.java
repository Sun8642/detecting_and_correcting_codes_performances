package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SyntheticDataGenerator {

    //Nearly 2 times slower than the other fct
//    public static String getRandomWord(int numberOfBits) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < numberOfBits; i++) {
//            stringBuilder.append(Math.random() >= 0.5d ? '1' : '0');
//        }
//        return stringBuilder.toString();
//    }

    public static String getRandomWord(int numberOfBits) {
        String s = Integer.toBinaryString((int) Math.floor(Math.random() * (1 << numberOfBits)));
        return s.length() == numberOfBits ? s : "0".repeat(numberOfBits - s.length()) + s;
    }

    public static BigInteger getBigIntegerRandomWord(int numberOfBits) {
        return new BigInteger(numberOfBits, new Random());
    }

    //a bit faster when numberOfBits is very low
//    public static String getRandomWord2(int numberOfBits) {
//        return Integer.toBinaryString((int) Math.floor(Math.random() * (1 << numberOfBits)) + (1 << numberOfBits)).substring(1);
//    }

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
