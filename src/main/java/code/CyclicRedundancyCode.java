package code;

import java.math.BigInteger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import math.BigInt;
import util.BitUtil;
import util.StringBuilderUtil;
import util.StringUtil;
import util.SyntheticDataGenerator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CyclicRedundancyCode {

    public static String encode(String message, String generatorPolynomial) {
        //Multiply Ps by Xr
        String encodedMessage = StringUtil.binaryLeftShift(message, generatorPolynomial.length() - 1);

        return message + getPolynomialArithmeticModulo2(encodedMessage, generatorPolynomial);
    }

    public static long encode(long message, long generatorPolynomial) {
        //Multiply Ps by Xr
        message <<= (BitUtil.leftMostSetBit(generatorPolynomial) - 1);

        return message + getPolynomialArithmeticModulo2(message, generatorPolynomial);
    }

    public static BigInteger encode(BigInteger message, BigInteger generatorPolynomial) {
        BigInteger encodedMessage = message.shiftLeft(generatorPolynomial.bitLength() - 1);
        return encodedMessage.add(getPolynomialArithmeticModulo2(encodedMessage, generatorPolynomial));
    }

    public static void encode(BigInt message, BigInt generatorPolynomial) {
        message.shiftLeft(generatorPolynomial.getLeftMostSetBit() - 1);
        message.add(getPolynomialArithmeticModulo2(message, generatorPolynomial));
    }

    public static String getPolynomialArithmeticModulo2(String dividend, String divisor) {
        StringBuilder remainder = new StringBuilder(dividend);
        int remainderLeftMostSetBit = StringBuilderUtil.binaryLeftMostSetBit(remainder, remainder.length());
        int divisorLeftMostSetBit = StringUtil.binaryLeftMostSetBit(divisor, divisor.length());

        int i, j;
        while (remainderLeftMostSetBit >= divisorLeftMostSetBit) {
            i = 0;
            while (i < divisorLeftMostSetBit) {
                j = remainder.length() - remainderLeftMostSetBit + i;
                if (divisor.charAt(divisor.length() - divisorLeftMostSetBit + i) == '1') {
                    remainder.setCharAt(j, remainder.charAt(j) == '0' ? '1' : '0');
                }
                i++;
            }
            remainderLeftMostSetBit = StringBuilderUtil.binaryLeftMostSetBit(remainder, remainderLeftMostSetBit - 1);
        }
        return remainder.substring(remainder.length() - divisor.length() + 1, remainder.length());
    }

    public static long getPolynomialArithmeticModulo2(long dividend, long divisor) {
        long remainder = dividend;
        int remainderLeftMostSetBit = BitUtil.leftMostSetBit(remainder);
        int divisorLeftMostSetBit = BitUtil.leftMostSetBit(divisor);

        while (remainderLeftMostSetBit >= divisorLeftMostSetBit) {
            remainder = remainder ^ (divisor << (remainderLeftMostSetBit - divisorLeftMostSetBit));
            remainderLeftMostSetBit = BitUtil.leftMostSetBit(remainder);
        }
        return remainder;
    }

    public static BigInteger getPolynomialArithmeticModulo2(BigInteger dividend, BigInteger divisor) {
        BigInteger remainder = dividend;
        int remainderLeftMostSetBit = remainder.bitLength();
        int divisorLeftMostSetBit = divisor.bitLength();

        while (remainderLeftMostSetBit >= divisorLeftMostSetBit) {
            remainder = remainder.xor(divisor.shiftLeft(remainderLeftMostSetBit - divisorLeftMostSetBit));
            remainderLeftMostSetBit = remainder.bitLength();
        }
        return remainder;
    }

    public static BigInt getPolynomialArithmeticModulo2(BigInt dividend, BigInt divisor) {
        BigInt remainder = new BigInt(dividend);
        divisor = new BigInt(divisor);
        int remainderLeftMostSetBit = remainder.getLeftMostSetBit();
        int newRemainderLeftMostSetBit;
        int divisorLeftMostSetBit = divisor.getLeftMostSetBit();

        if (remainderLeftMostSetBit > divisorLeftMostSetBit) {
            divisor.shiftLeft(remainderLeftMostSetBit - divisorLeftMostSetBit);
        }

        while (remainderLeftMostSetBit >= divisorLeftMostSetBit) {
//            remainder.xor(divisor);
            remainder.xor2(divisor);
            newRemainderLeftMostSetBit = remainder.getLeftMostSetBit();
            divisor.shiftRight(remainderLeftMostSetBit - newRemainderLeftMostSetBit);
            remainderLeftMostSetBit -= (remainderLeftMostSetBit - newRemainderLeftMostSetBit);
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

    public static double getSuccessRate(int iterations, double p, int messageBitSize, String generatorPolynomial) {
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
