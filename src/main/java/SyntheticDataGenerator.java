public final class SyntheticDataGenerator {

    private SyntheticDataGenerator() {

    }

    public static String getRandomWord(int numberOfBits) {
        return Integer.toBinaryString((int)(Math.random() * (Math.pow(2, numberOfBits + 1) - 1)));
    }
}
