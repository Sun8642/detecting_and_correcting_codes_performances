public final class HammingCode {

    private HammingCode() {

    }

    public static int hammingDistance(String codeWord1, String codeWord2) {
        return Integer.bitCount(Integer.parseInt(codeWord1, 2) ^ Integer.parseInt(codeWord2, 2));
    }

    public static int hammingDistance(String codeWord) {
        return hammingDistance(codeWord, "0");
    }

//    public static String encode(String message) {
//        StringBuilder encodedMessage = new StringBuilder(message);
//        int l = 0;
//        while (Math.pow(2, l) <= message.length()) {
//            encodedMessage.insert(message.length() + 1 - Math.pow(2, l), );
//            l++;
//        }
//        return encodedMessage.toString();
//    }
}
