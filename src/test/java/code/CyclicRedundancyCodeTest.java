package code;

import math.BigInt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

public class CyclicRedundancyCodeTest {

    @ParameterizedTest
    @CsvSource({
            "1011010,10110,101",
            "10110100,101101,101",
            "100100001,100100,1101",
            "10011101100,10011101,1001",
            "1101010000000010111110,110101,11000000000000101"
    })
    public void encode(StringBuilder expected, StringBuilder message, StringBuilder generatorPolynomial) {
        CyclicRedundancyCode.encode(message, generatorPolynomial);
        Assertions.assertEquals(expected.toString(), message.toString());
    }

    @ParameterizedTest
    @CsvSource({
//            "90,22,5",                       //1011010 = 90, 10110 = 22, 101 = 5
//            "180,45,5",                     //10110100 = 180, 101101 = 45, 101 = 5
//            "289,36,13",                    //100100001 = 289, 100100 = 36, 1101 = 13
//            "1260,157,9",                //10011101100 = 1260, 10011101 = 157, 1001 = 9
//            "3473598,53,98309"       //1101010000000010111110 = 3473598, 110101 = 53, 11000000000000101 = 98309
            "1011010,10110,101",
            "10110100,101101,101",
            "100100001,100100,1101",
            "10011101100,10011101,1001",
            "1101010000000010111110,110101,11000000000000101"
    })
    public void encodeBigInteger(String expected, String message, String generatorPolynomial) {
        Assertions.assertEquals(new BigInteger(expected, 2),
                CyclicRedundancyCode.encode(new BigInteger(message, 2), new BigInteger(generatorPolynomial, 2)));
    }

    @ParameterizedTest
    @CsvSource({
            "1011010,10110,101",
            "10110100,101101,101",
            "100100001,100100,1101",
            "10011101100,10011101,1001",
            "1101010000000010111110,110101,11000000000000101"
    })
    public void encodeBigInt(String expected, String message, String generatorPolynomial) {
        BigInt encodedMessage = new BigInt(Long.parseLong(message, 2));
        CyclicRedundancyCode.encode(encodedMessage, new BigInt(Long.parseLong(generatorPolynomial, 2)));
        Assertions.assertEquals(new BigInt(Long.parseLong(expected, 2)), encodedMessage);
    }

    @Test
    public void decode() {
        Assertions.assertEquals("10110", CyclicRedundancyCode.decode("1011010", "101"));
    }

    @Test
    public void decode_whenMessageIsCorrupted_shouldThrowException() {
        Assertions.assertThrows(RuntimeException.class, () -> CyclicRedundancyCode.decode("1011011", "101"));
    }

    @ParameterizedTest
    @CsvSource({
            "1011010,101",
            "10110100,101",
            "100100001,1101",
            "10011101100,1001",
            "1101010000000010111110,11000000000000101"
    })
    public void isCorrupted_whenDataIsNotCorrupted(String encodedMessage, String generatorPolynomial) {
        Assertions.assertFalse(CyclicRedundancyCode.isCorrupted(encodedMessage, generatorPolynomial));
    }

    @Test
    public void isCorrupted_whenMessageIsCorrupted() {
        Assertions.assertTrue(CyclicRedundancyCode.isCorrupted("1011011", "101"));
    }
}