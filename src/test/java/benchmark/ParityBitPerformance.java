package benchmark;

import code.ParityBitCode;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import math.BigInt;
import util.SyntheticDataGenerator;

public class ParityBitPerformance {

    @Test
    public void testPerformanceParityBit() {
        String message = "10110010110";
        BigInteger messageBigInteger = new BigInteger(message, 2);
        long messageLong = Long.parseLong(message, 2);
        long methodStartTime;


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(message);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(message);
        }
        System.out.println("temps pbc string: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(messageBigInteger);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(messageBigInteger);
        }
        System.out.println("temps pbc biginteger: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(new BigInt(messageLong));
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(new BigInt(messageLong));
        }
        System.out.println("temps pbc bigint: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(messageLong);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            ParityBitCode.encode(messageLong);
        }
        System.out.println("temps pbc long: " + (System.currentTimeMillis() - methodStartTime) + "ms");
    }

    @Test
    public void testPerformanceParityBit_bigMessage() {
        BigInteger messageBigInteger = SyntheticDataGenerator.getBigIntegerRandomWord(1500 * 8);
        String message = messageBigInteger.toString(2);
        BigInt messageBigInt = BigInt.from(messageBigInteger);
        long methodStartTime;


        //Warming up the VM
        for (int i = 0; i < 100000; i++) {
            ParityBitCode.encode(message);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ParityBitCode.encode(message);
        }
        System.out.println("temps pbc string: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 100000; i++) {
            ParityBitCode.encode(messageBigInteger);
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ParityBitCode.encode(messageBigInteger);
        }
        System.out.println("temps pbc biginteger: " + (System.currentTimeMillis() - methodStartTime) + "ms");


        //Warming up the VM
        for (int i = 0; i < 100000; i++) {
            ParityBitCode.encode(new BigInt(messageBigInt));
        }

        methodStartTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ParityBitCode.encode(new BigInt(messageBigInt));
        }
        System.out.println("temps pbc bigint: " + (System.currentTimeMillis() - methodStartTime) + "ms");
    }
}
