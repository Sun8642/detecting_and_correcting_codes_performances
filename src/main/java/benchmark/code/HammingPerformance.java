package benchmark.code;

import benchmark.Constant;
import code.HammingCode;
import math.BigInt;
import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;
import util.SyntheticDataGenerator;

import javax.swing.*;
import java.math.BigInteger;
import java.util.Arrays;

public class HammingPerformance {

    private static final int ITERATIONS = 100;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, hammingBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, hammingBigIntegerExecutionTimes());
        plot.addLinePlot("StringBuilder", numberOfBits, hammingStringExecutionTimes());
        plot.addLegend(PlotPanel.EAST);

        JFrame frame = new JFrame("Hamming execution time");
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

    private static double[] hammingBigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInt src;
        int numberOfBits = 1000;

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            HammingCode.encode(new BigInt(10, Constant.SPLITTABLE_RANDOM), true, 10);
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                HammingCode.encode(src, true, numberOfBits);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    private static double[] hammingBigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInteger src;
        int numberOfBits = 1000;

        src = new BigInteger(10, Constant.RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            HammingCode.encode(src, true, 10);
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInteger(numberOfBits, Constant.RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                HammingCode.encode(src, true, numberOfBits);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInteger : " + Arrays.toString(executionTime));
        return executionTime;
    }

    private static double[] hammingStringExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        StringBuilder src;
        int numberOfBits = 1000;

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            HammingCode.encode(SyntheticDataGenerator.getRandomStringBuilderWord(10), true);
        }

        for (int j = 0; j < 100; j++) {
            src = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                HammingCode.encode(src, true);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time StringBuilder : " + Arrays.toString(executionTime));
        return executionTime;
    }
}
