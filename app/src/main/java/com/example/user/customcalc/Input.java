package com.example.user.customcalc;

import android.widget.EditText;

import java.math.BigInteger;

/**
 * Created by user on 8/24/2016.
 */
public class Input {
    private static final String SPLIT_KEY="#";
    public static BigInteger getMod(MainActivity context) {
        EditText input = context.getView(R.id.eMod, EditText.class);
        return new BigInteger(input.getText().toString());
    }
    public static BigInteger[] getArgs(MainActivity context, int expectedCount) throws Exception {
        EditText input = context.getView(R.id.eArgs, EditText.class);
        String[] sArgs = input.getText().toString().split(SPLIT_KEY);
        if (sArgs.length!=expectedCount) {
            throw new Exception("UnexpectedNumber of args expected:" + expectedCount);
        }
        BigInteger[] result = new BigInteger[sArgs.length];
        for (int i = 0; i < sArgs.length; i++) {
            result[i] = new BigInteger(sArgs[i]);
        }
        return result;
    }
}
