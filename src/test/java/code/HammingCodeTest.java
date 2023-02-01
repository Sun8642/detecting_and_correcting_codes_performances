package code;

import code.HammingCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
            "0110001,01100000100,true",     //No error
            "11001010,110011010010,false",  //No error
            "0110001,01100000101,true",     //One error
            "0110001,01100100100,true",     //One error
            "0110110,01100110100,true",     //Two error, original message: 0110001, error can't be corrected correctly
    })
    public void decode(String expected, String encodedMessage, boolean parity) {
        Assertions.assertEquals(expected, HammingCode.decode(encodedMessage, parity));
    }
}
