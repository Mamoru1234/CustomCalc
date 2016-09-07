package org.alexei.math;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CanonicalFormat implements Iterable<NumberPower> {
    private final List<NumberPower> numbers = new LinkedList<>();

    private CanonicalFormat() {}

    public final void addNumberPower(BigInteger number, int power) {
        numbers.add(new NumberPower(number, power));
    }

    @Override
    public final Iterator<NumberPower> iterator() {
        return numbers.iterator();
    }

    public int size() {
        return numbers.size();
    }

    public static CanonicalFormat convertFrom(BigInteger number) throws NullPointerException, IllegalArgumentException, InterruptedException {
        if (number.compareTo(BigInteger.ONE) < 1) {
            throw new IllegalArgumentException();
        }
        final CanonicalFormat result = new CanonicalFormat();
        Generator generator = new PrimesGenerator();
        while (number.compareTo(BigInteger.ONE) == 1) {
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            for (BigInteger prime: generator.next()) {
                if (number.equals(BigInteger.ONE)) break;
                int power = 0;
                while (number.mod(prime).equals(BigInteger.ZERO)) {
                    power++;
                    number = number.divide(prime);
                }
                if (power != 0) {
                    result.addNumberPower(prime, power);
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (NumberPower rn : this) {
            sb.append(rn.getNumber() + " " + rn.getPower() + "\n");
        }
        return sb.toString();
    }

    public List<NumberPower> getNumbers() {
        return numbers;
    }
}
