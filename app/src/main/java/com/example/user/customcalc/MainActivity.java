package com.example.user.customcalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Output output;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout controls = getView(R.id.controls, GridLayout.class);
        controls.setColumnCount(3);
        controls.setRowCount(3);
        Button btn;
        ButtonDescription description;
        for(ButtonType btnType: ButtonType.values()) {
            try {
                btn = new Button(this);
                description = ButtonDescription.getButtonDescription(btnType);
                btn.setText(description.getLabel());
                btn.setOnClickListener(description.getListener());
                controls.addView(btn);
            } catch (ButtonDescription.UnknownButtonTypeException e) {
                showMessage(e.getMessage());
                e.printStackTrace();
            }
        }

        output = new Output();
    }
    public <T> T getView(int viewID, Class<T> tClass) {
        return (T) this.findViewById(viewID);
    }
    public void showMessage(final String message) {
        final MainActivity context = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static MainActivity getFromContext(View view) {
        return (MainActivity)view.getContext();
    }
    public Output getOutput() {
        return output;
    }
    public void displayOutput(final Output output) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView outputView = getView(R.id.output, TextView.class);
                outputView.setText(output.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: destroyed");
    }
}
