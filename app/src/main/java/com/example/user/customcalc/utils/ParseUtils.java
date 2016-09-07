package com.example.user.customcalc.utils;

import java.math.BigInteger;

public class ParseUtils {
    private static final String SPLIT_KEY = "#";

    public static BigInteger[] getArguments(String composedArgs) {
        String[] arguments = composedArgs.split(SPLIT_KEY);
        BigInteger[] result = new BigInteger[arguments.length];
        for (int index = 0; index < arguments.length; index++) {
            result[index] = new BigInteger(arguments[index]);
        }
        return result;
    }

    public static BigInteger getMod(String module) {
        return new BigInteger(module);
    }
}
