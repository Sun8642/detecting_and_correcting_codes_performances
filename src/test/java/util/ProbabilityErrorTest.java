package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ProbabilityErrorTest {

    @ParameterizedTest
    @CsvSource({
            "0.1485422,16,0.01",
            "0.1570568,17,0.01"
    })
    public void getProbabilityOfError(double expected, int N, double p) {
        Assertions.assertEquals(expected, ProbabilityError.getProbabilityOfError(N, p), 0.001d);
    }
}