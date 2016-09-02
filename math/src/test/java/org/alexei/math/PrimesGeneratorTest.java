package org.alexei.math;

import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by user on 8/22/2016.
 */
public class PrimesGeneratorTest {
    public List<BigInteger> convertToTestList(int[] numbers) {
        List<BigInteger> result = new LinkedList<>();
        for (int number: numbers) {
            result.add(BigInteger.valueOf(number));
        }
        return result;
    }
    @Test
    public void testDefaultConfig() throws InterruptedException {
        PrimesGenerator generator = new PrimesGenerator();
        int[] expected = new int[]{2,3,5,7,11,13,19,23,29,31};
        assertEquals("first generation", convertToTestList(expected), generator.next());
        expected = new int[]{37, 41, 43, 47, 53, 59, 61, 67, 71};
        assertEquals("second generation", convertToTestList(expected), generator.next());
        expected = new int[]{73, 79};
        assertEquals("third generation", convertToTestList(expected), generator.next());
        expected = new int[]{83, 89};
        assertEquals("fourth generation", convertToTestList(expected), generator.next());
        expected = new int[]{97, 101};
        assertEquals("fifth generation", convertToTestList(expected), generator.next());
        expected = new int[]{103, 107, 109};
        assertEquals("sixth generation", convertToTestList(expected), generator.next());
        expected = new int[]{113};
        assertEquals("seventh generation", convertToTestList(expected), generator.next());
    }
    @Test
    public void testCustomConfig() throws InterruptedException {
        PrimesGenerator generator = new PrimesGenerator(4);
        int[] expected = new int[]{2, 3, 5, 7};
        assertEquals("first generation", convertToTestList(expected), generator.next());
        expected = new int[]{11, 13, 19, 23};
        assertEquals("second generation", convertToTestList(expected), generator.next());
        expected = new int[]{29, 31, 37, 41};
        assertEquals("third generation", convertToTestList(expected), generator.next());
        expected = new int[]{43, 47, 53, 59};
        assertEquals("fourth generation", convertToTestList(expected), generator.next());
        expected = new int[]{61, 67, 71};
        assertEquals("fifth generation", convertToTestList(expected), generator.next());
        expected = new int[]{73};
        assertEquals("sixth generation", convertToTestList(expected), generator.next());
        expected = new int[]{79};
        assertEquals("seventh generation", convertToTestList(expected), generator.next());
    }
}
