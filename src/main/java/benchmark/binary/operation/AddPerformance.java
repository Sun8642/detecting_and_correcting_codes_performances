package benchmark.binary.operation;

import benchmark.Constant;
import java.math.BigInteger;
import java.util.Arrays;
import javax.swing.JFrame;
import math.BigInt;
import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;

public class AddPerformance {

    private static final int ADD_VALUE_BIT_COUNT = 16;
    private static final int ITERATIONS = 1000;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, addBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, addBigIntegerExecutionTimes());
        plot.addLegend(PlotPanel.EAST);

        JFrame frame = new JFrame("Add execution time");
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

    public static double[] addBigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInt src;
        BigInt addValue = new BigInt(ADD_VALUE_BIT_COUNT, Constant.SPLITTABLE_RANDOM);
        int numberOfBits = 1000;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
            src.add(addValue);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
                startingTime = System.nanoTime();
                src.add(addValue);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] addBigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        int numberOfBits = 1000;
        BigInteger src = new BigInteger(10, Constant.RANDOM);
        BigInteger addValue = new BigInteger(ADD_VALUE_BIT_COUNT, Constant.RANDOM);
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < Constant.WARMUP_ITERATIONS; i++) {
            src.add(addValue);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;
            for (int i = 0; i < ITERATIONS; i++) {
                src = new BigInteger(numberOfBits, Constant.RANDOM);
                startingTime = System.nanoTime();
                src.add(addValue);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInteger : " + Arrays.toString(executionTime));
        return executionTime;
    }
}
