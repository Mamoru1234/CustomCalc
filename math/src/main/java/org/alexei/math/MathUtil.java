package org.alexei.math;

import java.math.BigInteger;

/**
 * Created by user on 9/24/2016.
 */

public class MathUtil {
    public static BigInteger sqrt(BigInteger x) {
        if (x.compareTo(BigInteger.ZERO) < 1) throw new IllegalArgumentException();
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for(;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }
}
