package code;

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
    public void encode(String expected, String message, String generatorPolynomial) {
        Assertions.assertEquals(expected, CyclicRedundancyCode.encode(message, generatorPolynomial));
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

//    @Test
//    public void test() {
//        System.out.println(code.CyclicRedundancyCode.encode("1", "1000"));
//        Assertions.assertTrue(code.CyclicRedundancyCode.isCorrupted("1001", "1000"));
//        Assertions.assertTrue(code.CyclicRedundancyCode.isCorrupted("1010", "1000"));
//        Assertions.assertTrue(code.CyclicRedundancyCode.isCorrupted("1100", "1000"));
//        Assertions.assertTrue(code.CyclicRedundancyCode.isCorrupted("100", "1000"));
//        Assertions.assertFalse(code.CyclicRedundancyCode.isCorrupted("1000", "1000"));
//        Assertions.assertFalse(code.CyclicRedundancyCode.isCorrupted("10000", "1000"));
//        Assertions.assertFalse(code.CyclicRedundancyCode.isCorrupted("100000", "1000"));
//        Assertions.assertFalse(code.CyclicRedundancyCode.isCorrupted("1000000", "1000"));
//    }

    @Test
    public void test() {
        String encodedMessage = CyclicRedundancyCode.encode("11010", "1010100000");
        System.out.println(encodedMessage);
//        Assertions.assertTrue(code.CyclicRedundancyCode.isCorrupted("1001", "1000"));
//        Assertions.assertTrue(code.CyclicRedundancyCode.isCorrupted("1010", "1000"));
//        Assertions.assertTrue(code.CyclicRedundancyCode.isCorrupted("1100", "1000"));
//        Assertions.assertTrue(code.CyclicRedundancyCode.isCorrupted("100", "1000"));
        Assertions.assertFalse(CyclicRedundancyCode.isCorrupted(encodedMessage, "1010100000"));
        test2(encodedMessage);
//        Assertions.assertFalse(code.CyclicRedundancyCode.isCorrupted("10000", "1000"));
//        Assertions.assertFalse(code.CyclicRedundancyCode.isCorrupted("100000", "1000"));
//        Assertions.assertFalse(code.CyclicRedundancyCode.isCorrupted("1000000", "1000"));
    }

    private void test2(String encodedMsg) {
        for (int i = 0; i < encodedMsg.length() - 2; i++) {
            String messageWithError = addError(encodedMsg, "111", i);
            if (!CyclicRedundancyCode.isCorrupted(messageWithError, "1010100000") && !encodedMsg.equals(messageWithError)) {
                System.out.println("Not corrupted : " + messageWithError);
            }
        }
        for (int i = 0; i < encodedMsg.length() - 2; i++) {
            String messageWithError = addError(encodedMsg, "11", i);
            if (!CyclicRedundancyCode.isCorrupted(messageWithError, "1010100000") && !encodedMsg.equals(messageWithError)) {
                System.out.println("Not corrupted : " + messageWithError);
            }
        }
        for (int i = 0; i < encodedMsg.length() - 2; i++) {
            String messageWithError = addError(encodedMsg, "101", i);
            if (!CyclicRedundancyCode.isCorrupted(messageWithError, "1010100000") && !encodedMsg.equals(messageWithError)) {
                System.out.println("Not corrupted : " + messageWithError);
            }
        }
        for (int i = 0; i < encodedMsg.length() - 2; i++) {
            String messageWithError = addError(encodedMsg, "1", i);
            if (!CyclicRedundancyCode.isCorrupted(messageWithError, "1010100000") && !encodedMsg.equals(messageWithError)) {
                System.out.println("Not corrupted : " + messageWithError);
            }
        }
    }

    private String addError(String encodedMsg, String error, int j) {
        StringBuilder stringBuilder = new StringBuilder(encodedMsg);
        if (error.length() + j <= encodedMsg.length()) {
            for (int i = 0; i < error.length(); i++) {
                if (error.charAt(i) == '1') {
                    stringBuilder.replace(i + j, i + j + 1, stringBuilder.charAt(i + j) == '1' ? "0" : "1");
                }
            }
        }
        return stringBuilder.toString();
    }
}