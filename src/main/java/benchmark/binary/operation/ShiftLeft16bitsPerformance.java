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

public class ShiftLeft16bitsPerformance {

    private static final int SHIFT_LEFT_VALUE = 16;
    private static final int ITERATIONS = 1000;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, shiftLeftBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, shiftLeftBigIntegerExecutionTimes());
        plot.addLinePlot("StringBuilder", numberOfBits, shiftLeftStringBuilderExecutionTimes());
        plot.addLegend(PlotPanel.EAST);

        JFrame frame = new JFrame("Shift left execution time");
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

    public static double[] shiftLeftBigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInt src;
        int numberOfBits = 1000;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
            src.shiftLeft(SHIFT_LEFT_VALUE);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
                startingTime = System.nanoTime();
                src.shiftLeft(SHIFT_LEFT_VALUE);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] shiftLeftBigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        int numberOfBits = 1000;
        BigInteger src = new BigInteger(10, Constant.RANDOM);
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src.shiftLeft(1);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                src = new BigInteger(numberOfBits, Constant.RANDOM);
                startingTime = System.nanoTime();
                src.shiftLeft(SHIFT_LEFT_VALUE);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInteger : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] shiftLeftStringBuilderExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        int numberOfBits = 1000;
        StringBuilder src;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            StringBuilderUtil.binaryLeftShift(SyntheticDataGenerator.getRandomStringBuilderWord(10), 1);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                src = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);
                startingTime = System.nanoTime();
                StringBuilderUtil.binaryLeftShift(src, SHIFT_LEFT_VALUE);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time String : " + Arrays.toString(executionTime));
        return executionTime;
    }
}
