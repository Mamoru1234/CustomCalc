package com.example.user.customcalc;

import android.util.Log;

import org.alexei.math.CanonicalFormat;
import org.alexei.math.NumberPower;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by user on 8/24/2016.
 */
public class MathService{
    private static final String TAG = MathService.class.getName();
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    public static class UnknownButtonTypeException extends Exception{
        @Override
        public String getMessage() {
            return "Unknown button type";
        }
    }
    private static BigInteger applyModule(MainActivity context, BigInteger number) {
        try {
            BigInteger mod = Input.getMod(context);
            return number.mod(mod);
        }
        catch (NumberFormatException e) {
            // module is not correct provide addition without module
        }
        return number;
    }
    private static void performOperation(ButtonType type, final MainActivity context) throws UnknownButtonTypeException {
        try {
            Output output = context.getOutput();
            switch (type) {
                case CANONICAL: {
                    BigInteger[] args = Input.getArgs(context, 1);
                    List<NumberPower> numberPowerList =
                            CanonicalFormat.convertFrom(args[0]).getNumbers();
                    output.clear();
                    output.print("Number: "+args[0]+"=");
                    for (int i = 0; i < numberPowerList.size(); i++) {
                        NumberPower numberPower = numberPowerList.get(i);
                        output.print(numberPower.getNumber()+"^"+numberPower.getPower());
                        if (i != numberPowerList.size() - 1) {
                            output.print("*");
                        }
                    }
                    break;
                }
                case ADD: {
                    BigInteger[] args = Input.getArgs(context, 2);
                    BigInteger result = args[0].add(args[1]);
                    result = applyModule(context, result);
                    output.clear();
                    output.print("Result: "+result);
                    Log.d(TAG, output.toString());
                    break;
                }
                case MINUS: {
                    BigInteger[] args = Input.getArgs(context, 2);
                    BigInteger result = args[0].subtract(args[1]);
                    result = applyModule(context, result);
                    output.clear();
                    output.println("Result: "+result);
                    break;
                }
                case MULTIPLY: {
                    BigInteger[] args = Input.getArgs(context, 2);
                    BigInteger result = args[0].subtract(args[1]);
                    result = applyModule(context, result);
                    output.clear();
                    output.print("Result: "+result);
                    break;
                }
                case INVERT:{
                    BigInteger[] args = Input.getArgs(context, 1);
                    BigInteger mod = Input.getMod(context);
                    output.clear();
                    output.print("Invert: "+args[0].modInverse(mod));
                    break;
                }
                default: {
                    throw new UnknownButtonTypeException();
                }
            }
            context.displayOutput(output);
        }
        catch (InterruptedException e) {
            Log.d(TAG, "performOperation: canceled");
        }
        catch (Exception e) {
            context.showMessage(e.getMessage());
            e.printStackTrace();
        }
    }

    public static Future<?> perform(final ButtonType type, final MainActivity context) {
        return executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    performOperation(type, context);
                }
                catch (UnknownButtonTypeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static void shutdown() {
        executor.shutdown();
    }
}
