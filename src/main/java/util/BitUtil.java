package util;

import java.math.BigInteger;
import java.util.BitSet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import math.BigInt;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BitUtil {

    public static int leftMostSetBit(long number) {
        int posLeftMostSetBit = 0;
        while (number > 0) {
            number = number >> 1;
            posLeftMostSetBit++;
        }
        return posLeftMostSetBit;
    }

    public static int leftMostSetBit(StringBuilder number, int leftStartingPos) {
        int posLeftMostSetBit = leftStartingPos;
        while (posLeftMostSetBit > 0 && number.charAt(number.length() - posLeftMostSetBit) == '0') {
            posLeftMostSetBit--;
        }
        return posLeftMostSetBit;
    }

    public static int leftMostSetBit(String number, int leftStartingPos) {
        int posLeftMostSetBit = leftStartingPos;
        while (leftStartingPos > 0 && number.charAt(number.length() - posLeftMostSetBit) == '0') {
            posLeftMostSetBit++;
        }
        return posLeftMostSetBit;
    }

    public static boolean isPowerOfTwo(int number) {
        return (number != 0) && (number & (number - 1)) == 0;
    }

    public static boolean isNotPowerOfTwo(int number) {
        return !(isPowerOfTwo(number));
    }

    public static BigInteger insertBit(BigInteger bigInteger, int bitPosition, boolean bitSet) {
        BigInteger mask = BigInteger.ONE.shiftLeft(bitPosition).subtract(BigInteger.ONE);
        BigInteger rightVal = bigInteger.and(mask);
        return bigInteger.subtract(rightVal).shiftLeft(1).add(rightVal).add(bitSet ? BigInteger.ONE.shiftLeft(bitPosition) : BigInteger.ZERO);
    }

    public static void insertBit(BigInt bigInt, int bitPosition, boolean bitSet) {
        for (int i = bigInt.getLeftMostSetBit() - 1; i >= bitPosition; i--) {
            if (bigInt.testBit(i)) {
                bigInt.setBit(i + 1);
            } else {
                bigInt.clearBit(i + 1);
            }
        }
        if (bitSet) {
            bigInt.setBit(bitPosition);
        } else {
            bigInt.clearBit(bitPosition);
        }
    }

    public static long insertBit(long number, int bitPosition, boolean bitSet) {
        long mask = (1L << bitPosition) - 1;
        long rightVal = number & mask;
        return ((number - rightVal) << 1) + rightVal + (bitSet ? (1L << bitPosition) : 0L);
    }

    public static void insertBit(BitSet number, int bitPosition, boolean bitSet) {
        for (int i = number.length() - 1; i >= bitPosition; i--) {
            number.set(i + 1, number.get(i));
        }
        number.set(bitPosition, bitSet);
    }
}
