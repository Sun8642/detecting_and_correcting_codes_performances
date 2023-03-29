package code;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import math.BigInt;
import util.ProbabilityError;
import util.StringUtil;
import util.SyntheticDataGenerator;

import java.math.BigInteger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ParityBitCode {

    public static double getProbabilityOfDetectingError(int N, double p) {
        double probabilityOfDetectingError = 0.d;
        for (int k = 2; k <= N; k += 2) {
            probabilityOfDetectingError += ProbabilityError.getProbabilityOfKErrors(N, p, k);
        }
        return 1 - probabilityOfDetectingError;
    }

    public static String encode(String message) {
        return message + (isNumberOfOneEven(message) ? '0' : '1');
    }

    public static BigInteger encode(BigInteger message) {
        message = message.shiftLeft(1);
        return message.bitCount() % 2 == 0 ? message : message.add(BigInteger.ONE);
    }

    public static void encode(BigInt message) {
        message.shiftLeft(1);
        if (message.getBitCount() % 2 != 0) {
            message.add(1);
        }
    }

    public static long encode(long message) {
        message <<= 1;
        return Long.bitCount(message) % 2 == 0 ? message : (message + 1);
    }

    public static String decode(String encodedMessage) {
        if (isCorrupted(encodedMessage)) {
            throw new RuntimeException("Could not decode message, the encoded message is corrupted");
        }
        return encodedMessage.substring(0, encodedMessage.length() - 1);
    }

    public static BigInteger decode(BigInteger encodedMessage) {
        if (isCorrupted(encodedMessage)) {
            throw new RuntimeException("Could not decode message, the encoded message is corrupted");
        }
        return encodedMessage.shiftRight(1);
    }

    public static boolean isCorrupted(String message) {
        return !isNumberOfOneEven(message);
    }

    public static boolean isCorrupted(BigInteger message) {
        return message.bitCount() % 2 != 0;
    }

    private static boolean isNumberOfOneEven(String message) {
        return StringUtil.binaryBitCount(message) % 2 == 0;
    }

    public static double getErrorDetectionRate(int iterations, double p, int messageBitSize) {
        String message = SyntheticDataGenerator.getRandomWord(messageBitSize);
        String encodedMessage = encode(message);
        int nbMessageWithIntegrity = 0;
        int nbCorruptedMessageCorrectlyDetected = 0;

        String corruptedMessage;
        for (int i = 0; i < iterations; i++) {
            corruptedMessage = SyntheticDataGenerator.corruptWord(encodedMessage, p);
            if (encodedMessage.equals(corruptedMessage)) {
                nbMessageWithIntegrity++;
            } else {
                if (isCorrupted(corruptedMessage)) {
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
