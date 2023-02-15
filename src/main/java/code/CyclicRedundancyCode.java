package code;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.BitUtil;
import util.SyntheticDataGenerator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CyclicRedundancyCode {

    public static String encode(String message, String generatorPolynomial) {
        //Multiply Ps by Xr
        int encodedMessageInt = Integer.parseInt(message, 2) << (generatorPolynomial.length() - 1);
        int generatorPolynomialInt = Integer.parseInt(generatorPolynomial, 2);

        return Integer.toBinaryString(encodedMessageInt + getPolynomialArithmeticModulo2(encodedMessageInt, generatorPolynomialInt));
    }

    private static int getPolynomialArithmeticModulo2(int dividend, int divisor) {
        int remainder = dividend;
        int remainderLeftMostSetBit = BitUtil.leftMostSetBit(remainder);
        int divisorLeftMostSetBit = BitUtil.leftMostSetBit(divisor);

        while (remainderLeftMostSetBit >= divisorLeftMostSetBit) {
            remainder = remainder ^ (divisor << (remainderLeftMostSetBit - divisorLeftMostSetBit));
            remainderLeftMostSetBit = BitUtil.leftMostSetBit(remainder);
        }
        return remainder;
    }

    public static boolean isCorrupted(String encodedMessage, String generatorPolynomial) {
        int encodedMessageInt = Integer.parseInt(encodedMessage, 2);
        int generatorPolynomialInt = Integer.parseInt(generatorPolynomial, 2);
        return getPolynomialArithmeticModulo2(encodedMessageInt, generatorPolynomialInt) != 0;
    }

    public static boolean isCorrupted(int encodedMessage, int generatorPolynomial) {
        return getPolynomialArithmeticModulo2(encodedMessage, generatorPolynomial) != 0;
    }

    public static String decode(String encodedMessage, String generatorPolynomial) {
        if (isCorrupted(encodedMessage, generatorPolynomial)) {
            throw new RuntimeException("Could not decode message, the encoded message is corrupted");
        }
        return encodedMessage.substring(0, encodedMessage.length() - (generatorPolynomial.length() - 1));
    }

    public static double getProbabilityOfSuccess(int iterations, double p, int messageBitSize, String generatorPolynomial) {
        String message = SyntheticDataGenerator.getRandomWord(messageBitSize);
        String encodedMessage = encode(message, generatorPolynomial);
        int nbMessageWithIntegrity = 0;
        int nbCorruptedMessageCorrectlyDetected = 0;

        String corruptedMessage;
        for (int i = 0; i < iterations; i++) {
            corruptedMessage = SyntheticDataGenerator.corruptWord(encodedMessage, p);
            if (encodedMessage.equals(corruptedMessage)) {
                nbMessageWithIntegrity++;
            } else {
                if (isCorrupted(corruptedMessage, generatorPolynomial)) {
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
