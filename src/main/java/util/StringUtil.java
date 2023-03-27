package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    public static String binaryLeftShift(String binaryString, int shift) {
        return binaryString + "0".repeat(shift);
    }

    public static int binaryBitCount(String binaryString) {
        int numberOfOneCount = 0;
        int messageLength = binaryString.length();
        for (int i = 0; i < messageLength; i++) {
            if (binaryString.charAt(i) == '1') {
                numberOfOneCount++;
            }
        }
        return numberOfOneCount;
    }

    public static int binaryLeftMostSetBit(String number, int leftStartingPos) {
        int posLeftMostSetBit = leftStartingPos;
        while (posLeftMostSetBit > 0 && number.charAt(number.length() - posLeftMostSetBit) == '0') {
            posLeftMostSetBit--;
        }
        return posLeftMostSetBit;
    }
}
