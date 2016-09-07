package com.example.user.customcalc.operations;

import android.util.Log;

import org.alexei.math.CanonicalFormat;
import org.alexei.math.NumberPower;

import java.math.BigInteger;
import java.util.List;

public class CanonicalOperation extends Operation {

    public CanonicalOperation(String label) {
        super(label);
    }

    @Override
    public String eval(BigInteger[] arguments, BigInteger module) throws IllegalArgumentException, InterruptedException {
        checkArgumentCount(arguments, 1);
        StringBuilder builder = new StringBuilder();
        List<NumberPower> numberPowerList =
                CanonicalFormat.convertFrom(arguments[0]).getNumbers();
        builder.append("Number: " + arguments[0] + "=");
        for (int index = 0; index < numberPowerList.size(); index++) {
            NumberPower numberPower = numberPowerList.get(index);
            builder.append(numberPower.getNumber() + "^" + numberPower.getPower());
            if (index != numberPowerList.size() - 1) {
                builder.append("*");
            }
        }
        return builder.toString();
    }

}
