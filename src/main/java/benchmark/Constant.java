package benchmark;

import java.util.Random;
import java.util.SplittableRandom;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {

    public static final Random RANDOM = new Random();
    public static final SplittableRandom SPLITTABLE_RANDOM = new SplittableRandom();

    public final static int WARMUP_ITERATIONS = 10_000;
    public final static int NS_TO_MS = 1_000_000;
}
