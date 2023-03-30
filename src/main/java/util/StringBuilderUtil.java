package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringBuilderUtil {

    public static void binaryXor(StringBuilder stringBuilder, StringBuilder mask) {
        if (mask.length() > stringBuilder.length()) {
            int shift = mask.length() - stringBuilder.length();
            int minLength = stringBuilder.length();
            stringBuilder.insert(0, mask.substring(0, mask.length() - stringBuilder.length()));
            for (int i = 0; i < minLength; i++) {
                if (mask.charAt(shift + i) == '1') {
                    stringBuilder.setCharAt(i + shift, stringBuilder.charAt(i + shift) == '0' ? '1' : '0');
                }
            }
        } else {
            int minLength = mask.length();
            int shift = stringBuilder.length() - mask.length();
            for (int i = 0; i < minLength; i++) {
                if (mask.charAt(i) == '1') {
                    stringBuilder.setCharAt(i + shift, stringBuilder.charAt(i + shift) == '0' ? '1' : '0');
                }
            }
        }
    }

    public static void binaryLeftShift(StringBuilder binaryString, int shift) {
        binaryString.append("0".repeat(shift));
    }

    public static int binaryBitCount(StringBuilder binaryString) {
        int numberOfOneCount = 0;
        int messageLength = binaryString.length();
        for (int i = 0; i < messageLength; i++) {
            if (binaryString.charAt(i) == '1') {
                numberOfOneCount++;
            }
        }
        return numberOfOneCount;
    }

    public static int binaryLeftMostSetBit(StringBuilder number, int leftStartingPos) {
        int posLeftMostSetBit = leftStartingPos;
        while (posLeftMostSetBit > 0 && number.charAt(number.length() - posLeftMostSetBit) == '0') {
            posLeftMostSetBit--;
        }
        return posLeftMostSetBit;
    }

    public static boolean binaryTestBit(StringBuilder binaryString, int bitIndex) {
        //This is not safe if bitIndex is too high
        return binaryString.charAt(binaryString.length() - bitIndex) == '1';
    }
}
