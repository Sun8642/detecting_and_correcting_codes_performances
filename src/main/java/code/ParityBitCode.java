package code;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import math.BigInt;
import util.ProbabilityError;
import util.StringBuilderUtil;

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

    public static void encode(StringBuilder message) {
        message.append(StringBuilderUtil.binaryBitCount(message) % 2 == 0 ? '0' : '1');
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

    public static void decode(StringBuilder encodedMessage) {
        if (isCorrupted(encodedMessage)) {
            throw new RuntimeException("Could not decode message, the encoded message is corrupted");
        }
        encodedMessage.replace(encodedMessage.length() - 1, encodedMessage.length(), "");
    }

    public static BigInteger decode(BigInteger encodedMessage) {
        if (isCorrupted(encodedMessage)) {
            throw new RuntimeException("Could not decode message, the encoded message is corrupted");
        }
        return encodedMessage.shiftRight(1);
    }

    public static boolean isCorrupted(StringBuilder message) {
        return StringBuilderUtil.binaryBitCount(message) % 2 != 0;
    }

    public static boolean isCorrupted(BigInteger message) {
        return message.bitCount() % 2 != 0;
    }
}
