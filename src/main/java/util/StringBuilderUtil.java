package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringBuilderUtil {

    public static StringBuilder binaryXor(StringBuilder stringBuilder, StringBuilder mask) {
        if (mask.length() > stringBuilder.length()) {
            StringBuilder tmp = new StringBuilder(mask);
            binaryXorFirstArgNotSmallerThanSecondOne(tmp, stringBuilder);
            return tmp;
        } else {
            binaryXorFirstArgNotSmallerThanSecondOne(stringBuilder, mask);
            return stringBuilder;
        }
    }

    private static void binaryXorFirstArgNotSmallerThanSecondOne(StringBuilder stringBuilder, StringBuilder mask) {
        int minLength = Math.min(stringBuilder.length(), mask.length());
        int shift = stringBuilder.length() - mask.length();
        for (int i = 0; i < minLength; i++) {
            if (mask.charAt(i) == '1') {
                stringBuilder.setCharAt(i + shift, stringBuilder.charAt(i + shift) == '0' ? '1' : '0');
            }
        }
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
