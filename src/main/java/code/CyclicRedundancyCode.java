package code;

import util.BitUtil;

public final class CyclicRedundancyCode {

    private CyclicRedundancyCode() {

    }

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
}
