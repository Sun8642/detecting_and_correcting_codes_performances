package util;

public final class ProbabilityError {

    private ProbabilityError() {

    }

    public static double getProbabilityOfError(int N, double p) {
        return 1.d - Math.pow(1.d - p, N);
    }

    public static double getProbabilityOfKErrors(int N, double p, int k) {
        return getBinomialCoefficient(N, k) * Math.pow(p, k) * Math.pow((1.d - p), N - k);
    }

    public static double getBinomialCoefficient(int N, int k) {
        if (k == 0 || k == N) {
            return 1;
        }
        return getBinomialCoefficient(N - 1, k - 1) + getBinomialCoefficient(N - 1, k);
    }
}
