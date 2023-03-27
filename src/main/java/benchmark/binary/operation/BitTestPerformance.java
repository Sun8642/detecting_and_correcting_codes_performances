package benchmark.binary.operation;

import benchmark.Constant;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.SplittableRandom;
import javax.swing.JFrame;
import math.BigInt;
import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;
import util.StringBuilderUtil;
import util.StringUtil;
import util.SyntheticDataGenerator;

public class BitTestPerformance {

    private static final Random RANDOM = new Random();
    private static final SplittableRandom SPLITTABLE_RANDOM = new SplittableRandom();

    private static final int ITERATIONS = 1000000;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, bitTestBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, bitTestBigIntegerExecutionTimes());
        plot.addLinePlot("StringBuilder", numberOfBits, bitTestStringBuilderExecutionTimes());
        plot.addLegend(PlotPanel.EAST);

        JFrame frame = new JFrame("BitTest execution time");
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

    public static double[] bitTestBigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInt src;
        int numberOfBits = 1000;

        src = new BigInt(numberOfBits, SPLITTABLE_RANDOM);

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src.testBit(numberOfBits);
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInt(numberOfBits, SPLITTABLE_RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                src.testBit(1);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] bitTestBigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInteger src;
        int numberOfBits = 1000;
        src = new BigInteger(numberOfBits, RANDOM);

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src.testBit(numberOfBits);
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInteger(numberOfBits, RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                src.testBit(1);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time biginteger : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] bitTestStringBuilderExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        StringBuilder src;
        int numberOfBits = 1000;
        src = new StringBuilder(SyntheticDataGenerator.getRandomSplittableWord(numberOfBits));

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            StringBuilderUtil.binaryTestBit(src, numberOfBits);
        }

        for (int j = 0; j < 100; j++) {
            src = new StringBuilder(SyntheticDataGenerator.getRandomSplittableWord(numberOfBits));

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                StringBuilderUtil.binaryTestBit(src, 1);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time String : " + Arrays.toString(executionTime));
        return executionTime;
    }
}