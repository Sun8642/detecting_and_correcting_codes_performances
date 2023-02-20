package code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

public class ParityBitCodeTest {

    @ParameterizedTest
    @CsvSource({
            "0.989559,16,0.01",
            "0.671228,16,0.1"
    })
    public void getProbabilityOfDetectingError(double expected, int N, double p) {
        Assertions.assertEquals(expected, ParityBitCode.getProbabilityOfDetectingError(N, p), 0.001d);
    }

    @ParameterizedTest
    @CsvSource({
            "00000,0000",
            "11110,1111",
            "101101,10110",
            "1011010,101101",
    })
    public void encode(String expected, String message) {
        Assertions.assertEquals(expected, ParityBitCode.encode(message));
    }

    @ParameterizedTest
    @CsvSource({
            "00000,0000",
            "11110,1111",
            "101101,10110",
            "1011010,101101",
    })
    public void encodeBigInteger(String expected, String message) {
        Assertions.assertEquals(new BigInteger(expected, 2), ParityBitCode.encode(new BigInteger(message, 2)));
    }

    @ParameterizedTest
    @CsvSource({
            "0000,00000",
            "1111,11110",
            "10110,101101",
            "101101,1011010",
    })
    public void decode(String expected, String message) {
        Assertions.assertEquals(expected, ParityBitCode.decode(message));
    }

    @ParameterizedTest
    @CsvSource({
            "00001",
            "11111",
            "101100",
            "1011011",
    })
    public void decode_whenMessageIsCorrupted_shouldThrowException(String encodedMessage) {
        Assertions.assertThrows(RuntimeException.class, () -> ParityBitCode.decode(encodedMessage));
    }
}