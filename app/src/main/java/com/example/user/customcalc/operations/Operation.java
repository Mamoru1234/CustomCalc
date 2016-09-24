package com.example.user.customcalc.operations;

import com.example.user.customcalc.OperationType;

import java.math.BigInteger;

public abstract class Operation {
    private static final String TAG = Operation.class.getSimpleName();

    private final String label;

    public Operation(String label) {
        this.label = label;
    }

    public static Operation getButtonDescription(final OperationType type) {
        switch (type) {
            case ADD:
                return new AdditionOperation("+");
            case MINUS:
                return new SubtractionOperation("-");
            case MULTIPLY:
                return new MultiplicationOperation("*");
            case CANONICAL:
                return new CanonicalOperation("Canonical");
            case INVERT:
                return new InvertOperation("-1");
            default:
                return null;
        }
    }

    public String getLabel() {
        return label;
    }

    public void checkArgumentCount(BigInteger[] arguments, int requiredCount) {
        if (arguments.length != requiredCount) {
            throw new IllegalArgumentException("Required " + requiredCount + " arguments");
        }
    }

    public void checkModuleValue(BigInteger module) {
        if (module == null) {
            throw new IllegalArgumentException("Required mod");
        }
    }

    public abstract String eval(BigInteger[] arguments, BigInteger mod) throws IllegalArgumentException, InterruptedException;
}
