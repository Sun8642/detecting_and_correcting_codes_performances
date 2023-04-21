package benchmark.code;

import benchmark.Constant;
import code.CyclicRedundancyCode;
import math.BigInt;
import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;
import util.SyntheticDataGenerator;

import javax.swing.*;
import java.math.BigInteger;
import java.util.Arrays;

public class CrcPerformance {

    private static final int ITERATIONS = 10;
    private static final String crc32 = "100000100110000010001110110110111";
    private static final StringBuilder crc32StringBuilder = new StringBuilder(crc32);
    private static final BigInteger crc32BigInteger = new BigInteger("100000100110000010001110110110111", 2);
    private static final BigInt crc32BigInt = BigInt.from(crc32BigInteger);

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, crcBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, crcBigIntegerExecutionTimes());
        plot.addLinePlot("StringBuilder", numberOfBits, crcStringExecutionTimes());
        plot.addLegend(PlotPanel.EAST);

        JFrame frame = new JFrame("Crc execution time");
        frame.setContentPane(plot);
        frame.setVisible(true);
        frame.setSize(1000, 600);
    }

    private static double[] numberOfBits() {
        double[] numberOfBitsArray = new double[100];
        int numberOfBits = 1000;
        for (int j = 0; j < 100; j++) {
            numberOfBitsArray[j] = numberOfBits;
            numberOfBits += 1000;
        }
        return numberOfBitsArray;
    }

    public static double[] crcBigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInt src;
        BigInt generatorPolynomial = new BigInt(5L);
        int numberOfBits = 1000;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            CyclicRedundancyCode.encode(new BigInt(10, Constant.SPLITTABLE_RANDOM), crc32BigInt);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;

            for (int i = 0; i < ITERATIONS; i++) {
                src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
                startingTime = System.nanoTime();
                CyclicRedundancyCode.encode(src, crc32BigInt);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] crcBigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInteger src;
        BigInteger generatorPolynomial = BigInteger.valueOf(5L);
        int numberOfBits = 1000;

        src = new BigInteger(10, Constant.RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            CyclicRedundancyCode.encode(src, crc32BigInteger);
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInteger(numberOfBits, Constant.RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                CyclicRedundancyCode.encode(src, crc32BigInteger);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInteger : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] crcStringExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        StringBuilder src;
        StringBuilder generatorPolynomial = new StringBuilder("101");
        int numberOfBits = 1000;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            CyclicRedundancyCode.encode(SyntheticDataGenerator.getRandomStringBuilderWord(10), crc32StringBuilder);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;

            for (int i = 0; i < ITERATIONS; i++) {
                src = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);
                startingTime = System.nanoTime();
                CyclicRedundancyCode.encode(src, crc32StringBuilder);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time StringBuilder : " + Arrays.toString(executionTime));
        return executionTime;
    }
}
