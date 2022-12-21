import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

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