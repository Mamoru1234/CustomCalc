package com.example.user.customcalc;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 8/24/2016.
 */
public class Output {
    private StringBuilder stringBuilder = new StringBuilder();
    public void clear() {
        stringBuilder = new StringBuilder();
    }
    public void print(String message) {
        stringBuilder.append(message);
    }
    public void println(String message) {
        stringBuilder.append(message);
        stringBuilder.append("\n");
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
