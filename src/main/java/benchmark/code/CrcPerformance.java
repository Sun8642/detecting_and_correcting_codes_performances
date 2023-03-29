package benchmark.code;

import benchmark.Constant;
import code.CyclicRedundancyCode;
import java.math.BigInteger;
import java.util.Arrays;
import javax.swing.JFrame;
import math.BigInt;
import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;
import util.SyntheticDataGenerator;

public class CrcPerformance {

    private static final int ITERATIONS = 10;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, crcBigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, crcBigIntegerExecutionTimes());
        plot.addLinePlot("String", numberOfBits, crcStringExecutionTimes());
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

        src = new BigInt(10, Constant.SPLITTABLE_RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            CyclicRedundancyCode.encode(src, generatorPolynomial);
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                CyclicRedundancyCode.encode(src, generatorPolynomial);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
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
            CyclicRedundancyCode.encode(src, generatorPolynomial);
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInteger(numberOfBits, Constant.RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                CyclicRedundancyCode.encode(src, generatorPolynomial);
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
        String src;
        String generatorPolynomial = "101";
        int numberOfBits = 1000;

        src = SyntheticDataGenerator.getRandomWord(10);

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            CyclicRedundancyCode.encode(src, generatorPolynomial);
        }

        for (int j = 0; j < 100; j++) {
            src = SyntheticDataGenerator.getRandomWord(numberOfBits);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                CyclicRedundancyCode.encode(src, generatorPolynomial);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time String : " + Arrays.toString(executionTime));
        return executionTime;
    }
}
