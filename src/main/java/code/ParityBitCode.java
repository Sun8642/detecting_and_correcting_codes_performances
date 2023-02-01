package code;

import util.ProbabilityError;

public final class ParityBitCode {

    private ParityBitCode() {

    }

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

    public static String decode(String encodedMessage) {
        if (isCorrupted(encodedMessage)) {
            throw new RuntimeException("Could not decode message, the encoded message is corrupted");
        }
        return encodedMessage.substring(0, encodedMessage.length() - 1);
    }

    public static boolean isCorrupted(String message) {
        return !isNumberOfOneEven(message);
    }

    private static boolean isNumberOfOneEven(String message) {
        return message.chars().filter(ch -> ch == '1').count() % 2 == 0;
    }
}
