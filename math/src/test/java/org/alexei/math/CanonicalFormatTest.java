package org.alexei.math;

import org.alexei.math.CanonicalFormat;
import org.alexei.math.NumberPower;
import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created by user on 8/21/2016.
 */
public class CanonicalFormatTest {
    CanonicalFormat result;
    @Test(expected = NullPointerException.class)
    public void checkNull() throws InterruptedException {
        CanonicalFormat.convertFrom(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeNumber () throws InterruptedException {
        CanonicalFormat.convertFrom(BigInteger.valueOf(-10));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testZeroResult() throws InterruptedException {
        CanonicalFormat.convertFrom(BigInteger.valueOf(0));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testOneResult() throws InterruptedException {
        CanonicalFormat.convertFrom(BigInteger.ONE);
    }
    public CanonicalFormat convertFromInt(int number) throws InterruptedException {
        return CanonicalFormat.convertFrom(BigInteger.valueOf(number));
    }
    public void assertCanonical(CanonicalFormat result, NumberPower[] expected) {
        assertEquals("equal size", expected.length, result.size());
        int i = 0;
        for(NumberPower numberPower: result) {
            NumberPower expectedNP = expected[i];
            assertEquals("equalNumber["+i+"]", numberPower.getNumber(), expectedNP.getNumber());
            assertEquals("equalPower["+i+"]", numberPower.getPower(), expectedNP.getPower());
            i++;
        }
    }
    public NumberPower createNP(long number, int power) {
        return new NumberPower(BigInteger.valueOf(number), power);
    }
    @Test
    public void testWithPrimes() throws InterruptedException {
        result = convertFromInt(2);
        System.out.println(result);
        assertCanonical(result, new NumberPower[]{createNP(2,1)});
        result = convertFromInt(19);
        System.out.println(result);
        assertCanonical(result, new NumberPower[]{createNP(19,1)});
        result = convertFromInt(103);
        System.out.println(result);
        assertCanonical(result, new NumberPower[]{createNP(103,1)});
        result = convertFromInt(571);
        System.out.println(result);
        assertCanonical(result, new NumberPower[]{createNP(571,1)});
    }
    @Test
    public void testWithRandomPowers() throws InterruptedException {
        result = convertFromInt(4);
        System.out.println(result);
        assertCanonical(result, new NumberPower[]{createNP(2,2)});
        result = convertFromInt(81);
        System.out.println(result);
        assertCanonical(result, new NumberPower[]{createNP(3,4)});
        result = convertFromInt(2197);
        System.out.println(result);
        assertCanonical(result, new NumberPower[]{createNP(13,3)});
    }
    @Test
    public void testWithRandomComplexNumbers() throws InterruptedException {
        result = convertFromInt(145);
        System.out.println(result);
        assertCanonical(result, new NumberPower[] {
                createNP(5,1), createNP(29,1)
        });
        result = convertFromInt(6057739);
        System.out.println(result);
        assertCanonical(result, new NumberPower[] {
                createNP(103,2), createNP(571,1)
        });
    }
}
