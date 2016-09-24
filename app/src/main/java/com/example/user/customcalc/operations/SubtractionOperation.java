package com.example.user.customcalc.operations;

import java.math.BigInteger;

public class SubtractionOperation extends Operation {

    public SubtractionOperation(String label) {
        super(label);
    }

    @Override
    public String eval(BigInteger[] arguments, BigInteger module) throws IllegalArgumentException {
        checkArgumentCount(arguments, 2);
        checkModuleValue(module);
        BigInteger result = arguments[0].subtract(arguments[1]);
        result = result.mod(module);
        return "Result: " + result;
    }
}
