package benchmark.code;

import benchmark.Constant;
import code.CyclicRedundancyCode;
import math.BigInt;
import org.math.plot.Plot2DPanel;
import org.math.plot.PlotPanel;
import util.SyntheticDataGenerator;

import javax.swing.*;
import java.math.BigInteger;
import java.util.Arrays;

public class getPolynomialArithmeticModulo2Performance {

    private static final int ITERATIONS = 10;

    public static void main(String[] args) {
        double[] numberOfBits = numberOfBits();

        Plot2DPanel plot = new Plot2DPanel();

        plot.setAxisLabels("Number of bits", "Time for " + ITERATIONS + " executions (ms)");
        plot.addLinePlot("BigInt", numberOfBits, getPolynomialArithmeticModulo2BigIntExecutionTimes());
        plot.addLinePlot("BigInteger", numberOfBits, getPolynomialArithmeticModulo2BigIntegerExecutionTimes());
        plot.addLinePlot("StringBuilder", numberOfBits, getPolynomialArithmeticModulo2StringExecutionTimes());
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

    public static double[] getPolynomialArithmeticModulo2BigIntExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInt src;
        BigInt generatorPolynomial = new BigInt(5L);
        int numberOfBits = 1000;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            CyclicRedundancyCode.getPolynomialArithmeticModulo2(new BigInt(10, Constant.SPLITTABLE_RANDOM), generatorPolynomial);
        }

        for (int j = 0; j < 100; j++) {
            iterationTime = 0;

            for (int i = 0; i < ITERATIONS; i++) {
                src = new BigInt(numberOfBits, Constant.SPLITTABLE_RANDOM);
                startingTime = System.nanoTime();
                CyclicRedundancyCode.getPolynomialArithmeticModulo2(src, generatorPolynomial);
                endingTime = System.nanoTime();
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInt : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] getPolynomialArithmeticModulo2BigIntegerExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        BigInteger src;
        BigInteger generatorPolynomial = BigInteger.valueOf(5L);
        int numberOfBits = 1000;

        src = new BigInteger(10, Constant.RANDOM);

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            CyclicRedundancyCode.getPolynomialArithmeticModulo2(src, generatorPolynomial);
        }

        for (int j = 0; j < 100; j++) {
            src = new BigInteger(numberOfBits, Constant.RANDOM);

            startingTime = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                CyclicRedundancyCode.getPolynomialArithmeticModulo2(src, generatorPolynomial);
            }
            endingTime = System.nanoTime();
            executionTime[j] = ((double) endingTime - startingTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println("exec time BigInteger : " + Arrays.toString(executionTime));
        return executionTime;
    }

    public static double[] getPolynomialArithmeticModulo2StringExecutionTimes() {
        double[] executionTime = new double[100];
        long startingTime;
        long endingTime;
        StringBuilder src;
        StringBuilder generatorPolynomial = new StringBuilder("101");
        int numberOfBits = 1000;
        long iterationTime;

        //Warmup the jvm
        for (int i = 0; i < 10; i++) {
            CyclicRedundancyCode.getPolynomialArithmeticModulo2(SyntheticDataGenerator.getRandomStringBuilderWord(10), generatorPolynomial);
        }

        String[] test = new String[ITERATIONS];
        String polynomialArithmeticModulo2;
        for (int j = 0; j < 100; j++) {
            iterationTime = 0;

            for (int i = 0; i < ITERATIONS; i++) {
                src = SyntheticDataGenerator.getRandomStringBuilderWord(numberOfBits);
                startingTime = System.nanoTime();
                polynomialArithmeticModulo2 = CyclicRedundancyCode.getPolynomialArithmeticModulo2(src, generatorPolynomial);
                endingTime = System.nanoTime();
                test[i] = polynomialArithmeticModulo2;
                iterationTime += endingTime - startingTime;
            }
            executionTime[j] = ((double) iterationTime) / Constant.NS_TO_MS;
            numberOfBits += 1000;
        }
        System.out.println(test[5]);
        System.out.println("exec time StringBuilder : " + Arrays.toString(executionTime));
        return executionTime;
    }
}
