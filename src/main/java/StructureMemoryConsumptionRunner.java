import math.BigInt;
import org.openjdk.jol.info.ClassLayout;

import java.math.BigInteger;
import java.util.Random;

public class StructureMemoryConsumptionRunner {

    public static void main(String[] args) {
        bigInt();
        bigInteger();
        string();
    }

    public static void bigInt() {
        BigInt bigInt32bits = new BigInt(1L);
        bigInt32bits.shiftLeft(31);
        System.out.println(ClassLayout.parseInstance(bigInt32bits).toPrintable());
        System.out.println("============================================================");
        BigInt bigInt96bits = new BigInt(1L);
        bigInt96bits.shiftLeft(95);
        System.out.println(ClassLayout.parseInstance(bigInt96bits).toPrintable());
        System.out.println("------------------------------------------------------------");
        System.out.println(ClassLayout.parseInstance(bigInt96bits.getDig()).toPrintable());
        System.out.println("============================================================");
        BigInt bigInt2048bits = new BigInt(2048, new Random());
        System.out.println(ClassLayout.parseInstance(bigInt2048bits).toPrintable());
        System.out.println("------------------------------------------------------------");
        System.out.println(ClassLayout.parseInstance(bigInt2048bits.getDig()).toPrintable());
        System.out.println("============================================================");
    }

    public static void bigInteger() {
        BigInteger bigInt32bits = BigInteger.ONE;
        bigInt32bits = bigInt32bits.shiftLeft(31);
        System.out.println(ClassLayout.parseInstance(bigInt32bits).toPrintable());
        System.out.println("============================================================");
        BigInteger bigInt96bits = BigInteger.ONE;
        bigInt96bits = bigInt96bits.shiftLeft(95);
        System.out.println(ClassLayout.parseInstance(bigInt96bits).toPrintable());
        System.out.println("============================================================");
        BigInteger bigInt2048bits = BigInteger.ONE;
        bigInt2048bits = bigInt2048bits.shiftLeft(2047);
        System.out.println(ClassLayout.parseInstance(bigInt2048bits).toPrintable());
        System.out.println("============================================================");
        int[] arrayInt = new int[3];
        arrayInt[0] = 1 << 31;
        System.out.println(ClassLayout.parseInstance(arrayInt).toPrintable());
        System.out.println("============================================================");
        int[] arrayInt2 = new int[1];
        arrayInt2[0] = 1 << 31;
        System.out.println(ClassLayout.parseInstance(arrayInt2).toPrintable());
    }

    public static void string() {
        String stringTest = "memory";
        System.out.println(ClassLayout.parseInstance(stringTest).toPrintable());
        System.out.println("------------------------------------------------------------");
        System.out.println(ClassLayout.parseInstance(stringTest.getBytes()).toPrintable());
        System.out.println("============================================================");
        String string32char = "a".repeat(32);
        System.out.println(ClassLayout.parseInstance(string32char).toPrintable());
        System.out.println("============================================================");
        String string64char = "a".repeat(64);
        System.out.println(ClassLayout.parseInstance(string64char).toPrintable());
        System.out.println("============================================================");
        String string2048char = "a".repeat(2048);
        System.out.println(ClassLayout.parseInstance(string2048char).toPrintable());
        System.out.println("------------------------------------------------------------");
        System.out.println(ClassLayout.parseInstance(string2048char.getBytes()).toPrintable());
        System.out.println("============================================================");
    }
}
