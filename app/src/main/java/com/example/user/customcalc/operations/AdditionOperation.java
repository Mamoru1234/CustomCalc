package com.example.user.customcalc.operations;

import java.math.BigInteger;

public class AdditionOperation extends Operation {
    public AdditionOperation(String label) {
        super(label);
    }

    @Override
    public String eval(BigInteger[] arguments, BigInteger module) throws IllegalArgumentException {
        checkArgumentCount(arguments, 2);
        checkModuleValue(module);
        BigInteger result = arguments[0].add(arguments[1]);
        result = result.mod(module);
        return "Result: " + result;
    }

}
