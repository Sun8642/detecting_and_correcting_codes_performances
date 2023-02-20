package code;

import model.HammingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.SyntheticDataGenerator;

import java.math.BigInteger;

public class HammingCodeTest {

    @ParameterizedTest
    @CsvSource({"3,100101", "0,0000000000000"})
    public void hammingDistance_onSingleWord(int expected, String codeWord) {
        Assertions.assertEquals(expected, HammingCode.hammingDistance(codeWord));
    }

    @ParameterizedTest
    @CsvSource({
            "6,101010,010101",      //All bits are different
            "2,000111,100110",      //Only some bits are different
            "0,111111,111111",      //Bits are identical
            "5,1111100000,00000"    //First word is longer than the second one
    })
    public void hammingDistance_betweenTwoCodeWords(int expected, String codeWord1, String codeWord2) {
        Assertions.assertEquals(expected, HammingCode.hammingDistance(codeWord1, codeWord2));
    }

    /*
    *  Montrer que d H ainsi définie est une distance sur V n . 2. Montrer que si V est binaire, d H ( x, y ) = w ( x ⊕ y ) .
    * */
    @Test
    public void hammingDistance_distanceBetweenTwoWordsIsEqualToWeightOfXorOfTwoWords() {
        String a = "10101010";
        String b = "11110000";
        int aInt = Integer.parseInt(a, 2);
        int bInt = Integer.parseInt(b, 2);
        Assertions.assertEquals(HammingCode.hammingDistance(a, b), HammingCode.hammingDistance(Integer.toBinaryString(aInt ^ bInt)));
    }

    @ParameterizedTest
    @CsvSource({
            "01100000100,0110001,true",
            "110011010010,11001010,false"
    })
    public void encode(String expected, String messageToEncode, boolean parity) {
        Assertions.assertEquals(expected, HammingCode.encode(messageToEncode, parity));
    }

    @ParameterizedTest
    @CsvSource({
            "01100000100,0110001,true",
            "110011010010,11001010,false"
    })
    public void encodeBigInteger(String expected, String messageToEncode, boolean parity) {
        Assertions.assertEquals(new BigInteger(expected, 2), HammingCode.encode(new BigInteger(messageToEncode, 2), parity, messageToEncode.length()));
    }

    @ParameterizedTest
    @CsvSource({
            "01100000100,0110001,true",
            "110011010010,11001010,false"
    })
    public void encodeLong(String expected, String messageToEncode, boolean parity) {
        Assertions.assertEquals(Long.parseLong(expected, 2), HammingCode.encode(Long.parseLong(messageToEncode, 2), parity, messageToEncode.length()));
    }

    @Test
    public void testPerformanceParityBit() {
        String message = "10110010110";
        BigInteger messageBigInteger = new BigInteger(message, 2);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(message);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("temps pbc string: " + (l1 - l));
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(messageBigInteger);
        }
        System.out.println("temps pbc biginteger: " + (System.currentTimeMillis() - l1));
    }

    @Test
    public void testPerformanceInternetChecksum() {
        String message = "101011011100101010101101110010101010110111001010";
        BigInteger messageBigInteger = new BigInteger(message, 2);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(message);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("temps InternetChecksum string: " + (l1 - l));
        for (int i = 0; i < 1000000; i++) {
            InternetChecksum.encode(messageBigInteger);
        }
        System.out.println("temps InternetChecksum biginteger: " + (System.currentTimeMillis() - l1));
    }

    @Test
    public void testPerformanceCRC() {
        String message = "10010101010101010101010";
        String generatorPolynomial = "1000110110010101";
        BigInteger messageBigInteger = new BigInteger(message, 2);
        BigInteger generatorPolynomialBigInteger = new BigInteger(generatorPolynomial, 2);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(message, generatorPolynomial);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("temps CyclicRedundancyCode string: " + (l1 - l));
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(messageBigInteger, generatorPolynomialBigInteger);
        }
        System.out.println("temps CyclicRedundancyCode biginteger: " + (System.currentTimeMillis() - l1));
    }

    @Test
    public void testPerformanceHamming() {
        String message = "10110010110";
        BigInteger messageBigInteger = new BigInteger(message, 2);
        long messageLong = Long.parseLong(message, 2);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(message, true);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("temps hamming string: " + (l1 - l));
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(messageBigInteger, true, 11);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("temps hamming biginteger: " + (l2 - l1));
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(messageLong, true, 11);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("temps hamming long: " + (l3 - l2));
    }

    @Test
    public void testPerformanceHamming2() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(SyntheticDataGenerator.getRandomWord(11), true);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("temps hamming string: " + (l1 - l));
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(SyntheticDataGenerator.getBigIntegerRandomWord(11), true, 11);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("temps hamming biginteger: " + (l2 - l1));
        for (int i = 0; i < 1000000; i++) {
            HammingCode.encode(SyntheticDataGenerator.getLongRandomWord(11), true, 11);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("temps hamming long: " + (l3 - l2));
    }

    @ParameterizedTest
    @CsvSource({
            "false,0110001,01100000100,true",     //No error
            "false,11001010,110011010010,false",  //No error
            "true,0110001,01100000101,true",     //One error
            "true,0110001,01100100100,true",     //One error
            "true,0110110,01100110100,true",     //Two error, original message: 0110001, error can't be corrected correctly
    })
    public void decode(boolean isErrorDetectedExpected, String decodedMessageExpected, String encodedMessage, boolean parity) {
        HammingResponse hammingResponse = HammingCode.decode(encodedMessage, parity);
        Assertions.assertEquals(decodedMessageExpected, hammingResponse.getDecodedMessage());
        Assertions.assertEquals(isErrorDetectedExpected, hammingResponse.isErrorDetected());
    }
}
