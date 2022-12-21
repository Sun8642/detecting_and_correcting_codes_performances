public final class InternetChecksum {

    public static final String REGEX_STRING_16_CHARS = "(?<=\\G.{16})";

    private InternetChecksum() {

    }

    public static String getChecksum(String message) {
        if (message == null || message.length() == 0) {
            return "";
        }
        return Integer.toBinaryString(getChecksumInt(message));
    }

    private static int getChecksumInt(String message) {
        return (~getSumOfWords(message)) & 0xffff;
    }

    public static boolean isCorrupted(String message, String checksum) {
        return getSumOfWords(message) + Integer.parseInt(checksum, 2) != 0xffff;
    }

    private static int getSumOfWords(String message) {
        //TODO: works only if message.length() % 16 == 0
        String[] words = message.split(REGEX_STRING_16_CHARS);
        int checksum = Integer.parseInt(words[0], 2);
        for (int i = 1; i < words.length; i++) {
            checksum += Integer.parseInt(words[i], 2);
        }

        return (checksum & 0xffff) + (checksum >> 16);
    }
}
