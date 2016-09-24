package org.alexei.math;

import java.math.BigInteger;

public class NumberPower {
    private final BigInteger number;
    private final int power;

    public NumberPower(BigInteger number, int power) {
        this.number = number;
        this.power = power;
    }

    public BigInteger getNumber() {
        return number;
    }

    public int getPower() {
        return power;
    }
}
