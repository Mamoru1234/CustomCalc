package com.example.user.customcalc.operations;

import java.math.BigInteger;

public class InvertOperation extends Operation {

    public InvertOperation(String label) {
        super(label);
    }

    @Override
    public String eval(BigInteger[] arguments, BigInteger module) throws IllegalArgumentException {
        checkArgumentCount(arguments, 1);
        checkModuleValue(module);
        return "Invert: " + arguments[0].modInverse(module);
    }
}
