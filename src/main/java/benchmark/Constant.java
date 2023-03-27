package benchmark;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {

    public final static int WARMUP_ITERATIONS = 10_000;
    public final static int NS_TO_MS = 1_000_000;
}
