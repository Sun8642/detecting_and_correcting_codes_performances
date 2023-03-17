package benchmark;

import code.CyclicRedundancyCode;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import math.BigInt;
import util.SyntheticDataGenerator;

public class CrcPerformance {

    @Test
    public void testPerformanceCRC() {
        String message = "10010101010101010101010";
        String generatorPolynomial = "1000110110010101";

        long messageLong = Long.parseLong("10010101010101010101010", 2);
        long generatorPolynomialLong = Long.parseLong("1000110110010101", 2);

        BigInteger messageBigInteger = new BigInteger(message, 2);
        BigInteger generatorPolynomialBigInteger = new BigInteger(generatorPolynomial, 2);

        BigInt messageBigInt = new BigInt(messageLong);
        BigInt generatorPolynomialBigInt = new BigInt(generatorPolynomialLong);

        long methodStartTime;


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(message, generatorPolynomial);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(message, generatorPolynomial);
        }
        System.out.println("temps CyclicRedundancyCode string: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(message, generatorPolynomial);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(messageLong, generatorPolynomialLong);
        }
        System.out.println("temps CyclicRedundancyCode long: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(messageBigInteger, generatorPolynomialBigInteger);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(messageBigInteger, generatorPolynomialBigInteger);
        }
        System.out.println("temps CyclicRedundancyCode BigInteger: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(new BigInt(messageBigInt), new BigInt(generatorPolynomialBigInt));
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.encode(new BigInt(messageBigInt), new BigInt(generatorPolynomialBigInt));
        }
        System.out.println("temps CyclicRedundancyCode bigint: " + (System.currentTimeMillis() - methodStartTime) + "ms");
    }

    @Test
    public void testPerformanceCRC_bigMessage() {
        BigInteger bigMsgBigInteger = SyntheticDataGenerator.getBigIntegerRandomWord(1500 * 8);
        BigInteger crc32BigInteger = new BigInteger("100000100110000010001110110110111", 2);

        String message = bigMsgBigInteger.toString(2);
        String generatorPolynomial = crc32BigInteger.toString(2);

        BigInt messageBigInt = BigInt.from(bigMsgBigInteger);
        BigInt crc32BigInt = BigInt.from(crc32BigInteger);

        long methodStartTime;


        for (int i = 0; i < 1000; i++) {
            CyclicRedundancyCode.encode(message, generatorPolynomial);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            CyclicRedundancyCode.encode(message, generatorPolynomial);
        }
        System.out.println("temps CyclicRedundancyCode string: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        for (int i = 0; i < 1000; i++) {
            CyclicRedundancyCode.encode(bigMsgBigInteger, crc32BigInteger);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            CyclicRedundancyCode.encode(bigMsgBigInteger, crc32BigInteger);
        }
        System.out.println("temps CyclicRedundancyCode BigInteger: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        for (int i = 0; i < 1000; i++) {
            CyclicRedundancyCode.encode(new BigInt(messageBigInt), new BigInt(crc32BigInt));
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            CyclicRedundancyCode.encode(new BigInt(messageBigInt), new BigInt(crc32BigInt));
        }
        System.out.println("temps CyclicRedundancyCode bigint: " + (System.currentTimeMillis() - methodStartTime) + "ms");
    }

    @Test
    public void testPerformancePolynomialArithmeticModulo2() {
        String message = "10010101010101010101010";
        String generatorPolynomial = "1000110110010101";

        int dividend = Integer.parseInt(message, 2);
        dividend = dividend << (generatorPolynomial.length() - 1);
        int generatorPolyInt = Integer.parseInt(generatorPolynomial, 2);
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.getPolynomialArithmeticModulo2(dividend, generatorPolyInt);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("temps getPolynomialArithmeticModulo2 int: " + (l2 - l1));

        BigInteger dividendBigInteger = BigInteger.valueOf(dividend);
        BigInteger generatorPolyBigInteger = BigInteger.valueOf(generatorPolyInt);
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.getPolynomialArithmeticModulo2(dividendBigInteger, generatorPolyBigInteger);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("temps getPolynomialArithmeticModulo2 biginteger: " + (l3 - l2));

        BigInt dividendBigInt = new BigInt(dividend);
        BigInt generatorPolyBigInt = new BigInt(generatorPolyInt);
        for (int i = 0; i < 1000000; i++) {
            CyclicRedundancyCode.getPolynomialArithmeticModulo2(dividendBigInt, generatorPolyBigInt);
        }
        long l4 = System.currentTimeMillis();
        System.out.println("temps getPolynomialArithmeticModulo2 bigint: " + (l4 - l3));
    }
}
