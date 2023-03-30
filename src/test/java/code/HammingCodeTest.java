package code;

import math.BigInt;
import model.HammingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.BitSet;

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
    public void encode(String expected, StringBuilder message, boolean parity) {
        HammingCode.encode(message, parity);
        Assertions.assertEquals(expected, message.toString());
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
    public void encodeBigInt(String expected, String messageToEncode, boolean parity) {
        BigInt messageToEncodeBigInt = new BigInt(Long.parseLong(messageToEncode, 2));
        HammingCode.encode(messageToEncodeBigInt, parity, messageToEncode.length());
        Assertions.assertEquals(new BigInt(Long.parseLong(expected, 2)), messageToEncodeBigInt);
    }

    @ParameterizedTest
    @CsvSource({
            "01100000100,0110001,true",
            "110011010010,11001010,false"
    })
    public void encodeLong(String expected, String messageToEncode, boolean parity) {
        Assertions.assertEquals(Long.parseLong(expected, 2), HammingCode.encode(Long.parseLong(messageToEncode, 2), parity, messageToEncode.length()));
    }

    @ParameterizedTest
    @CsvSource({
            "01100000100,0110001,true",
            "110011010010,11001010,false"
    })
    public void encodeBitSet(String expected, String messageToEncode, boolean parity) {
        BitSet bitSet = BitSet.valueOf(new long[]{Long.parseLong(messageToEncode, 2)});
        HammingCode.encode(bitSet, parity, messageToEncode.length());
        Assertions.assertEquals(BitSet.valueOf(new long[]{Long.parseLong(expected, 2)}), bitSet);
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
