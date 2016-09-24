package com.example.user.customcalc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.user.customcalc.operations.Operation;
import com.example.user.customcalc.utils.UIUtils;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MathService {
    private static final String TAG = MathService.class.getSimpleName();
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    private static Future<?> currentTask;

    public static void perform(final BigInteger[] arguments, final BigInteger mod,
                               final Operation operation, final Activity activity) {
        if (currentTask != null && !currentTask.isCancelled() && !currentTask.isDone()) {
            currentTask.cancel(true);
            UIUtils.showMessage(activity, "Previous task has been canceled");
        }
        currentTask = executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = operation.eval(arguments, mod);
                    sendResultToBroadCastReceiver(activity, result);
                } catch (IllegalArgumentException e) {
                    UIUtils.showMessage(activity, e.getMessage());
                    Log.d(TAG, "Exception has occurred: " + e.getMessage());
                } catch (InterruptedException e) {
                    Log.d(TAG, "InterruptedException has occurred");
                }
            }
        });
    }

    public static void shutdown() {
        executor.shutdown();
    }

    private static void sendResultToBroadCastReceiver(Context context, String result) {
        Intent intent = new Intent(MainActivity.OPERATION_RESULT_ACTION);
        intent.putExtra(MainActivity.RESULT_KEY, result);
        context.sendBroadcast(intent);
    }

}
