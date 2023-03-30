package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.BitSet;

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
            "111011,11111,2,false",
            "10001,1000,0,true",
            "10000,1000,0,false",
            "10101010,1001010,5,true",
            "100010,00010,5,true",
            "000010,00010,5,false",
    })
    public void insertBitStringBuilder(String expected, String binaryString, int bitPosition, boolean bitSet) {
        StringBuilder binaryStringBuilder = new StringBuilder(binaryString);
        BitUtil.insertBit(binaryStringBuilder, bitPosition, bitSet);
        Assertions.assertEquals(expected, binaryStringBuilder.toString());
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
    public void insertBitBigInteger(String expected, String binaryBigInteger, int bitPosition, boolean bitSet) {
        Assertions.assertEquals(new BigInteger(expected, 2), BitUtil.insertBit(new BigInteger(binaryBigInteger, 2), bitPosition, bitSet));
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
    public void insertBitLong(String expected, String binaryBigInteger, int bitPosition, boolean bitSet) {
        Assertions.assertEquals(Long.parseLong(expected, 2), BitUtil.insertBit(Long.parseLong(binaryBigInteger, 2), bitPosition, bitSet));
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
    public void insertBitBitSet(String expected, String binaryBigInteger, int bitPosition, boolean bitSet) {
        BitSet number = BitSet.valueOf(new long[]{Long.parseLong(binaryBigInteger, 2)});
        BitUtil.insertBit(number, bitPosition, bitSet);
        Assertions.assertEquals(BitSet.valueOf(new long[]{Long.parseLong(expected, 2)}), number);
    }
}