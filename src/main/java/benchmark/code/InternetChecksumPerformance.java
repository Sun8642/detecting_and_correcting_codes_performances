package benchmark.code;

import benchmark.Constant;
import code.InternetChecksum;
import math.BigInt;
import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;
import util.SyntheticDataGenerator;

import javax.swing.*;
import java.math.BigInteger;
import java.util.Arrays;

public class InternetChecksumPerformance {

    private static final int ITERATIONS = 10;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, internetChecksumBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, internetChecksumBigIntegerExecutionTimes());
        plot.addLinePlot("StringBuilder", numberOfBits, internetChecksumStringExecutionTimes());
        plot.addLegend(PlotPanel.EAST);

        JFrame frame = new JFrame("Internet checksum execution time");
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

    private static double[] internetChecksumBigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInt src;
        int numberOfBits = 1000;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            InternetChecksum.encode(new BigInt(10, Constant.SPLITTABLE_RANDOM));
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
                startingTime = System.nanoTime();
                InternetChecksum.encode(src);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    private static double[] internetChecksumBigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInteger src;
        int numberOfBits = 1000;

        src = new BigInteger(10, Constant.RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            InternetChecksum.encode(src);
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInteger(numberOfBits, Constant.RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                InternetChecksum.encode(src);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInteger : " + Arrays.toString(executionTime));
        return executionTime;
    }

    private static double[] internetChecksumStringExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        StringBuilder src;
        int numberOfBits = 1000;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            InternetChecksum.encode(SyntheticDataGenerator.getRandomStringBuilderWord(10));
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;

            for (int i = 0; i < ITERATIONS; i++) {
                src = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);
                startingTime = System.nanoTime();
                InternetChecksum.encode(src);
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
