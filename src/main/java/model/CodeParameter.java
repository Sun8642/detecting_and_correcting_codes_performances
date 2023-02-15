package model;

import lombok.Getter;

@Getter
public class CodeParameter {

    protected int numberOfIterationsPerProbability = 10000;

    protected int messageBitSize = 8;

    protected double minP = 0.01d;
    protected double maxP = 0.5d;
    protected int numberOfStep = 50;

    protected boolean canCorrectError = false;

    protected boolean isBurstError = false;
    protected int burstErrorLength = 3;

    public void setNumberOfIterationsPerProbability(int numberOfIterationsPerProbability) {
        if (numberOfIterationsPerProbability < 1) {
            throw new IllegalArgumentException("The number of iterations per probability must be at least one.");
        }
        this.numberOfIterationsPerProbability = numberOfIterationsPerProbability;
    }

    public void setMessageBitSize(int messageBitSize) {
        if (messageBitSize < 1) {
            throw new IllegalArgumentException("The number of bits per message must be at least one.");
        }
        this.messageBitSize = messageBitSize;
    }

    public void setMinP(double minP) {
        if (minP <= 0.d || minP > 1.d) {
            throw new IllegalArgumentException("The probability to have an error must be ]0, 1].");
        }
        if (minP > maxP) {
            maxP = minP;
        }
        this.minP = minP;
    }

    public void setMaxP(double maxP) {
        if (maxP <= 0.d || maxP > 1.d) {
            throw new IllegalArgumentException("The probability to have an error must be ]0, 1].");
        }
        if (minP > maxP) {
            minP = maxP;
        }
        this.maxP = maxP;
    }

    public void setNumberOfStep(int numberOfStep) {
        if (messageBitSize < 1) {
            throw new IllegalArgumentException("The number of bits per message must be at least one.");
        }
        this.numberOfStep = numberOfStep;
    }

    public void setBurstError(boolean burstError) {
        isBurstError = burstError;
    }

    public void setBurstErrorLength(int burstErrorLength) {
        if (messageBitSize < 1) {
            throw new IllegalArgumentException("The burst error length must be greater than zero.");
        }
        this.burstErrorLength = burstErrorLength;
    }
}
