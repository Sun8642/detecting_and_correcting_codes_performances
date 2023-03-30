package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringBuilderUtilTest {

    @ParameterizedTest
    @CsvSource({
            "000,111,111",
            "111111,111000,000111",
            "111111,111000,111",
            "111011,111000,11",
            "111111,111,111000",
            "111011,11,111000",
    })
    public void binaryXor(String expected, StringBuilder binaryStringBuilder, StringBuilder mask) {
        StringBuilderUtil.binaryXor(binaryStringBuilder, mask);
        Assertions.assertEquals(expected, binaryStringBuilder.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "1100,1100,0",
            "11000,1100,1",
            "110000000,1100,5"
    })
    public void binaryLeftShift(String expected, StringBuilder binaryString, int leftShift) {
        StringBuilderUtil.binaryLeftShift(binaryString, leftShift);
        Assertions.assertEquals(expected, binaryString.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "0,000000",
            "3,101010",
            "6,111111"
    })
    public void binaryBitCount(int expected, StringBuilder binaryString) {
        Assertions.assertEquals(expected, StringBuilderUtil.binaryBitCount(binaryString));
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "1,000001",
            "6,100000",
            "3,00100",
    })
    public void binaryLeftMostSetBit(int expected, StringBuilder binaryStringBuilder) {
        Assertions.assertEquals(expected, StringBuilderUtil.binaryLeftMostSetBit(binaryStringBuilder, binaryStringBuilder.length()));
    }

    @ParameterizedTest
    @CsvSource({
            "false,0,1",
            "true,1,1",
            "true,100000,6",
            "false,00100,5",
            "true,00100,3",
    })
    public void binaryTestBit(boolean expected, StringBuilder binaryStringBuilder, int bitIndex) {
        Assertions.assertEquals(expected, StringBuilderUtil.binaryTestBit(binaryStringBuilder, bitIndex));
    }
}