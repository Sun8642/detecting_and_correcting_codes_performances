package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class BitUtilTest {

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "1,1",
            "4,8",
            "4,15"
    })
    public void leftMostSetBit(int expected, int number) {
        Assertions.assertEquals(expected, BitUtil.leftMostSetBit(number));
    }

    @ParameterizedTest
    @CsvSource({
            "false,0",
            "true,1",
            "true,2",
            "false,3",
            "true,16",
            "false,15",
    })
    public void isPowerOfTwo(boolean expected, int number) {
        Assertions.assertEquals(expected, BitUtil.isPowerOfTwo(number));
    }

    @ParameterizedTest
    @CsvSource({
            "true,0",
            "false,1",
            "false,2",
            "true,3",
            "false,16",
            "true,15",
    })
    public void isNotPowerOfTwo(boolean expected, int number) {
        Assertions.assertEquals(expected, BitUtil.isNotPowerOfTwo(number));
    }

    @ParameterizedTest
    @CsvSource({
            "10100,1000,2,true",
            "10001,1000,0,true",
            "10000,1000,0,false",
            "10101010,1001010,5,true",
            "100010,10,5,true",
            "10,10,5,false",
    })
    public void insertBit(String expected, String binaryBigInteger, int bitPosition, boolean bitSet) {
        Assertions.assertEquals(new BigInteger(expected, 2), BitUtil.insertBit(new BigInteger(binaryBigInteger, 2), bitPosition, bitSet));
    }
}