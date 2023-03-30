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

public class XorPerformance {

    private static final int ITERATIONS = 10000;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, xorBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, xorBigIntegerExecutionTimes());
        plot.addLinePlot("StringBuilder", numberOfBits, xorStringBuilderExecutionTimes());
        plot.addLegend(PlotPanel.EAST);

        JFrame frame = new JFrame("Xor execution time");
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

    public static double[] xorBigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInt src;
        BigInt mask;
        int numberOfBits = 1000;

        src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
        mask = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src.xor(mask);
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
            mask = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                src.xor2(mask);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] xorBigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInteger src;
        BigInteger mask;
        int numberOfBits = 1000;
        src = new BigInteger(numberOfBits, Constant.RANDOM);
        mask = new BigInteger(numberOfBits, Constant.RANDOM);

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src.xor(mask);
        }

        for (int j = 0; j < 100; j++) {
//            numberOfBits = (int) Math.pow(10.d, j + 1.d);
            src = new BigInteger(numberOfBits, Constant.RANDOM);
            mask = new BigInteger(numberOfBits, Constant.RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                src.xor(mask);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time biginteger : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] xorStringBuilderExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        StringBuilder src;
        StringBuilder mask;
        int numberOfBits = 1000;
        src = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);
        mask = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            StringBuilderUtil.binaryXor(src, mask);
        }

        for (int j = 0; j < 100; j++) {
            src = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);
            mask = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                StringBuilderUtil.binaryXor(src, mask);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time StringBuilder : " + Arrays.toString(executionTime));
        return executionTime;
    }
}
