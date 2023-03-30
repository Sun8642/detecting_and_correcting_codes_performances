package benchmark;

import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;
import util.SyntheticDataGenerator;

import javax.swing.*;
import java.util.Arrays;

public class SyntheticDataGeneratorPerformance {

    private static final int ITERATIONS = 10000;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, generateRandomBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, generateRandomBigIntegerExecutionTimes());
        plot.addLinePlot("StringBuilder", numberOfBits, generateRandomStringBuilderExecutionTimes());
        plot.addLegend(PlotPanel.EAST);

        JFrame frame = new JFrame("Generating random number execution time");
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

    public static double[] generateRandomBigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        int numberOfBits = 1000;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            SyntheticDataGenerator.getBigIntRandomWord(10);
        }

        for (int j = 0; j < 100; j++) {

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                SyntheticDataGenerator.getBigIntRandomWord(numberOfBits);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] generateRandomBigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        int numberOfBits = 1000;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            SyntheticDataGenerator.getBigIntegerRandomWord(10);
        }

        for (int j = 0; j < 100; j++) {

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                SyntheticDataGenerator.getBigIntegerRandomWord(numberOfBits);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time biginteger : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] generateRandomStringBuilderExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        int numberOfBits = 1000;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            SyntheticDataGenerator.getRandomStringBuilderWord(10);
        }

        for (int j = 0; j < 100; j++) {

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time StringBuilder : " + Arrays.toString(executionTime));
        return executionTime;
    }
}
