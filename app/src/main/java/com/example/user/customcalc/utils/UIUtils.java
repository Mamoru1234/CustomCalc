package com.example.user.customcalc.utils;

import android.app.Activity;
import android.widget.Toast;

public class UIUtils {

    public static void showMessage(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static <T> T getView(Activity activity, int viewID) {
        return (T) activity.findViewById(viewID);
    }

}
