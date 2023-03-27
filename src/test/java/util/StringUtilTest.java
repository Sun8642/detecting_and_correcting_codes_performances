package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilTest {

    @ParameterizedTest
    @CsvSource({
            "1100,1100,0",
            "11000,1100,1",
            "110000000,1100,5"
    })
    public void binaryLeftShift(String expected, String binaryString, int leftShift) {
        Assertions.assertEquals(expected, StringUtil.binaryLeftShift(binaryString, leftShift));
    }

    @ParameterizedTest
    @CsvSource({
            "0,000000",
            "3,101010",
            "6,111111"
    })
    public void binaryBitCount(int expected, String binaryString) {
        Assertions.assertEquals(expected, StringUtil.binaryBitCount(binaryString));
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "1,000001",
            "6,100000",
            "3,00100",
    })
    public void binaryLeftMostSetBit(int expected, String binaryString) {
        Assertions.assertEquals(expected, StringUtil.binaryLeftMostSetBit(binaryString, binaryString.length()));
    }
}