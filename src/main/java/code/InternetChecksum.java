package code;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import math.BigInt;
import util.SyntheticDataGenerator;

import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InternetChecksum {

    public static final String REGEX_STRING_16_CHARS = "(?<=\\G.{16})";
    private static final BigInteger MASK_16_BITS = BigInteger.valueOf(0xffff);
    private static final BigInt MASK_16_BITS_BIG_INT = new BigInt(0xffff);

    public static String encode(String message) {
        return message + getChecksum(message);
    }

    public static BigInteger encode(BigInteger message) {
        return message.shiftLeft(16).add(getChecksum(message));
    }

    public static void encode(BigInt message) {
        BigInt checksum = getChecksum(message);
        message.shiftLeft(16);
        message.add(checksum);
    }

    public static long encode(long message) {
        return (message << 16) + getChecksum(message);
    }

    public static String decode(String encodedMessage) {
        if (isCorrupted(encodedMessage)) {
            throw new RuntimeException("Could not decode message, the encoded message is corrupted");
        }
        return encodedMessage.substring(0, encodedMessage.length() - 16);
    }

    public static String getChecksum(String message) {
        return Long.toBinaryString(getChecksumInt(message));
    }

    public static BigInteger getChecksum(BigInteger message) {
        return getSumOfWords(message).not().and(MASK_16_BITS);
    }

    public static BigInt getChecksum(BigInt message) {
        BigInt sumOfWords = getSumOfWords(message);
        sumOfWords.not();
        sumOfWords.and(MASK_16_BITS_BIG_INT);
        return sumOfWords;
    }

    private static long getChecksumInt(String message) {
        return (~getSumOfWords(message)) & 0xffff;
    }

    private static long getChecksum(long message) {
        return (~getSumOfWords(message)) & 0xffff;
    }

    public static boolean isCorrupted(String encodedMessage) {
        return getSumOfWords(encodedMessage.substring(0, encodedMessage.length() - 16)) + Integer.parseInt(encodedMessage.substring(encodedMessage.length() - 16), 2) != 0xffff;
    }

    private static long getSumOfWords(String message) {
        //Works only if message.length() % 16 == 0
        String[] words = message.split(REGEX_STRING_16_CHARS);
        long checksum = Integer.parseInt(words[0], 2);
        for (int i = 1; i < words.length; i++) {
            checksum += Integer.parseInt(words[i], 2);
        }

        return (checksum & 0xffff) + (checksum >> 16);
    }

    private static long getSumOfWords(long message) {
        long sumOfWords = 0;
        for (int i = 0; i < 4; i++) {
            sumOfWords += (message & 0xffff);
            message = message >> 16;
        }

        return (sumOfWords & 0xffff) + (sumOfWords >> 16);
    }

    private static BigInteger getSumOfWords(BigInteger message) {
        int length = ((int) Math.ceil((float) message.bitLength() / 16)) * 16;
        BigInteger result = message;
        while (length > 16) {
            length -= 16;
            result = result.shiftRight(16).add(result.mod(BigInteger.ONE.shiftLeft(16)));
        }

        return result.and(MASK_16_BITS).add(result.shiftRight(16));
    }

    private static BigInt getSumOfWords(BigInt message) {
        int length = ((int) Math.ceil((float) message.getLeftMostSetBit() / 16)) * 16;
        BigInt result = new BigInt(0L);
        BigInt messageCopy = new BigInt(message);
        //calculate sum of each segment of 16 bits
        while (length > 16) {
            length -= 16;
            result.add((messageCopy.getLeastSignificantInt() & 0xffff));
            messageCopy.shiftRight(16);
        }
        result.add(messageCopy.getLeastSignificantInt());

        //We must add all the bits in overflow (all the bits that are in position greater than 16)
        int leftMostSetBit = result.getLeftMostSetBit();
        int tmp;
        while (leftMostSetBit > 16) {
            tmp = result.getLeastSignificantInt() & 0xffff;
            result.shiftRight(16);
            result.add(tmp);
            leftMostSetBit = result.getLeftMostSetBit();
        }
        return result;
    }

    public static double getProbabilityOfSuccess(int iterations, double p, int messageBitSize) {
        String message = SyntheticDataGenerator.getRandomWord(messageBitSize);
        String encodedMessage = InternetChecksum.encode(message);
        int nbMessageWithIntegrity = 0;
        int nbCorruptedMessageCorrectlyDetected = 0;

        String corruptedMessage;
        for (int i = 0; i < iterations; i++) {
            corruptedMessage = SyntheticDataGenerator.corruptWord(encodedMessage, p);
            if (encodedMessage.equals(corruptedMessage)) {
                nbMessageWithIntegrity++;
            } else {
                if (InternetChecksum.isCorrupted(corruptedMessage)) {
                    nbCorruptedMessageCorrectlyDetected++;
                }
            }
        }
        if (iterations - nbMessageWithIntegrity == 0) {
            return 1.d;
        }
        return (double) nbCorruptedMessageCorrectlyDetected / (iterations - nbMessageWithIntegrity);
    }
}
