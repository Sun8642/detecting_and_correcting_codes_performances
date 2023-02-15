package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BitUtil {

    public static int leftMostSetBit(int number) {
        int posLeftMostSetBit = 0;
        while (number > 0) {
            number = number >> 1;
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
}
