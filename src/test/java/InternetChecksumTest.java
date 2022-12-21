import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class InternetChecksumTest {

    @ParameterizedTest
    @CsvSource({
            "1111111111111111,0000000000000000",
            "1011010100111101,011001100110000001010101010101011000111100001100"
    })
    public void getChecksum(String expected, String message) {
        Assertions.assertEquals(expected, InternetChecksum.getChecksum(message));
    }

    @ParameterizedTest
    @CsvSource({
            "false,0000000000000000,1111111111111111",
            "false,011001100110000001010101010101011000111100001100,1011010100111101",
            "true,0000000010000000,1111111111111111",
            "true,011001100110000001010101010101011000111100001100,1011010000111101",


            //All message belows are based on the message:
            //     011001100110000001010101010101011000111100001100 with the checksum: 1011010100111101
            "false,011101100110000001000101010101011000111100001100,1011010100111101",  //Corrupted, the 4th bit of the first and second word were flipped
            "true,111001100110000011010101010101011000111100001100,1011010100111101",   //Corrupted, the first bit of the first and second word were flipped
            "false,011001100110000001010101010101011000111100001101,1011010100111100",  //Corrupted, the 16th bit of the last word and the checksum were flipped
            "false,011001100110000001010101010101011000111100001010,1011010100111111",  //Corrupted, the 14th and 15th bit of the last word and the 15th bit of the checksum were flipped
    })
    public void isCorrupted(boolean isCorrupted, String message, String checksum) {
        Assertions.assertEquals(isCorrupted, InternetChecksum.isCorrupted(message, checksum));
    }
}