package benchmark.binary.operation;

import benchmark.Constant;
import math.BigInt;
import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;
import util.StringBuilderUtil;
import util.SyntheticDataGenerator;

import javax.swing.*;
import java.math.BigInteger;
import java.util.Arrays;

public class BitCountPerformance {

    private static final int ITERATIONS = 10000;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, bitCountBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, bitCountBigIntegerExecutionTimes());
        plot.addLinePlot("StringBuilder", numberOfBits, bitCountStringBuilderExecutionTimes());
        plot.addLegend(PlotPanel.EAST);

        JFrame frame = new JFrame("BitCount execution time");
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

    public static double[] bitCountBigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInt src;
        int numberOfBits = 1000;

        src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src.getBitCount();
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                src.getBitCount();
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] bitCountBigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInteger src;
        int numberOfBits = 1000;
        src = new BigInteger(numberOfBits, Constant.RANDOM);

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src.bitCount();
        }

        for (int j = 0; j < 100; j++) {
//            numberOfBits = (int) Math.pow(10.d, j + 1.d);
            src = new BigInteger(numberOfBits, Constant.RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                src.bitCount();
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time biginteger : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] bitCountStringBuilderExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        StringBuilder src;
        int numberOfBits = 1000;
        src = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            StringBuilderUtil.binaryBitCount(src);
        }

        for (int j = 0; j < 100; j++) {
            src = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                StringBuilderUtil.binaryBitCount(src);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time StringBuilder : " + Arrays.toString(executionTime));
        return executionTime;
    }
}
