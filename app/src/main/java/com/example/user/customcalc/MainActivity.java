package com.example.user.customcalc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;

import com.example.user.customcalc.utils.ParseUtils;
import com.example.user.customcalc.utils.UIUtils;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int GRID_VIEW_COLUMN_COUNT = 3;

    public static final String OPERATION_RESULT_ACTION = "operation.result.action";
    public static final String RESULT_KEY = "result";

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView controls = UIUtils.getView(this, R.id.controls);
        controls.setAdapter(new OperationAdapter(this, OperationType.values()));
        controls.setNumColumns(GRID_VIEW_COLUMN_COUNT);
        registerBroadCastReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    public void registerBroadCastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(OPERATION_RESULT_ACTION);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                TextView outputView = UIUtils.getView(MainActivity.this, R.id.output);
                Bundle bundle = intent.getExtras();
                outputView.setText(bundle.getString(RESULT_KEY));
            }
        };
        registerReceiver(broadcastReceiver, filter);
    }

    public BigInteger[] getInputArguments() {
        final EditText inputView = UIUtils.getView(this, R.id.eArgs);
        String arguments = inputView.getText().toString();
        return !arguments.isEmpty() ? ParseUtils.getArguments(arguments) : new BigInteger[0];
    }

    public BigInteger getMod() {
        final EditText inputView = UIUtils.getView(this, R.id.eMod);
        String module = inputView.getText().toString();
        return !module.isEmpty() ? ParseUtils.getMod(module) : null;
    }
}
