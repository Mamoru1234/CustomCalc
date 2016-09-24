package org.alexei.math;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 9/24/2016.
 */

public class MathUtilTest {
    private static final int RANDOW_ROUNDS_COUNT = 1000;
    private void assertNumbers(String message, long value, long expected) {
        assertEquals(message, BigInteger.valueOf(expected), MathUtil.sqrt(BigInteger.valueOf(value)));
    }
    @Test
    public void testSqrt() {
        assertNumbers("sqrt(9)", 9, 3);
        assertNumbers("sqrt(4)", 4, 2);
        assertNumbers("sqrt(5)", 5, 2);
    }
    @Test
    public void testRandomRounds() {
        Random random = new Random();
        for (int i=0; i < RANDOW_ROUNDS_COUNT; i++) {
            long number = random.nextLong();
            while (number<0) {
                number = random.nextLong();
            }
            assertNumbers("sqrt(" + number + ")", number, (long) Math.sqrt(number));
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void zeroSQRTException() {
        assertNumbers("sqrt(0)", 0, 0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void negativeSQRTException() {
        assertNumbers("sqrt(-1)", -1, -1);
    }

}
