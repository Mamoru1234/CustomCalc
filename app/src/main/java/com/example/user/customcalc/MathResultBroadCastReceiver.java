package com.example.user.customcalc;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.customcalc.utils.UIUtils;

/**
 * Created by user on 9/24/2016.
 */

public class MathResultBroadCastReceiver extends BroadcastReceiver {
    private final Activity activity;
    public MathResultBroadCastReceiver (Activity activity) {
        this.activity = activity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        TextView outputView = UIUtils.getView(activity, R.id.output);
        Bundle bundle = intent.getExtras();
        outputView.setText(bundle.getString(MainActivity.RESULT_KEY));
    }
}
