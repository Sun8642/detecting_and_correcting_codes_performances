package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class StringBuilderUtilTest {

    @ParameterizedTest
    @CsvSource({
            "000,111,111",
            "111111,111000,000111",
            "111111,111000,111",
            "111111,111,111000",
    })
    public void binaryXor(String expected, StringBuilder binaryStringBuilder, StringBuilder mask) {
        Assertions.assertEquals(expected, StringBuilderUtil.binaryXor(binaryStringBuilder, mask).toString());
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