package math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.BitUtil;
import static org.junit.jupiter.api.Assertions.*;

public class BigIntTest {

    @ParameterizedTest
    @CsvSource({
            "10100,1000,2,true",
            "10001,1000,0,true",
            "10000,1000,0,false",
            "10101010,1001010,5,true",
            "100010,10,5,true",
            "10,10,5,false",
    })
    public void insertBitBigInt(String expected, String binaryBigIntStr, int bitPosition, boolean bitSet) {
        BigInt expectedBigInt = new BigInt(Long.parseLong(expected, 2));
        BigInt binaryBigInt = new BigInt(Long.parseLong(binaryBigIntStr, 2));
        binaryBigInt.insertBit(bitPosition, bitSet);
        Assertions.assertEquals(expectedBigInt, binaryBigInt);
    }
}