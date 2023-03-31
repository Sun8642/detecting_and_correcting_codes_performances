package benchmark.binary.operation;

import benchmark.Constant;
import math.BigInt;
import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;
import util.BitUtil;
import util.SyntheticDataGenerator;

import javax.swing.*;
import java.math.BigInteger;
import java.util.Arrays;

public class InsertBitAtPosPerformance {

    private static final int ITERATIONS = 1000;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, insertBitAtPosBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, insertBitAtPosBigIntegerExecutionTimes());
        plot.addLinePlot("StringBuilder", numberOfBits, insertBitAtPosStringBuilderExecutionTimes());
        plot.addLegend(PlotPanel.EAST);

        JFrame frame = new JFrame("Insert bit at position execution time");
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

    public static double[] insertBitAtPosBigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInt src;
        int numberOfBits = 1000;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
            src.insertBit(0, false);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
                startingTime = System.nanoTime();
                src.insertBit(numberOfBits, false);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] insertBitAtPosBigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        int numberOfBits = 1000;
        BigInteger src = new BigInteger(10, Constant.RANDOM);
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            BitUtil.insertBit(src, 0, false);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                src = new BigInteger(numberOfBits, Constant.RANDOM);
                startingTime = System.nanoTime();
                BitUtil.insertBit(src, numberOfBits, false);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInteger : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] insertBitAtPosStringBuilderExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        int numberOfBits = 1000;
        StringBuilder src;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src = SyntheticDataGenerator.getRandomStringBuilderWord(10);
            BitUtil.insertBit(src, 0, false);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                src = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);
                startingTime = System.nanoTime();
                BitUtil.insertBit(src, numberOfBits, false);
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
