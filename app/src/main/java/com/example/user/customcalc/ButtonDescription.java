package com.example.user.customcalc;

import android.util.Log;
import android.view.View;

import java.util.concurrent.Future;

public class ButtonDescription {
    private final String label;
    private final ButtonType buttonType;
    private static final String TAG = "ButtonDescription";
    private static Future<?> currentTask;
    private ButtonDescription(String label, ButtonType buttonType) {
        this.label = label;
        this.buttonType = buttonType;
    }
    public static class UnknownButtonTypeException extends Exception{
        @Override
        public String getMessage() {
            return "Unknown button type";
        }
    }
    public static ButtonDescription getButtonDescription(final ButtonType type) throws UnknownButtonTypeException {
        switch (type) {
            case ADD: {
                return new ButtonDescription("+", type);
            }
            case MINUS: {
                return new ButtonDescription("-", type);
            }
            case MULTIPLY:{
                return new ButtonDescription("*", type);
            }
            case CANONICAL: {
                return new ButtonDescription("Canonical", type);
            }
            case INVERT: {
                return new ButtonDescription("-1", type);
            }
        }
        throw new UnknownButtonTypeException();
    }

    public String getLabel() {
        return label;
    }
    private void cancelPreviousTask(MainActivity context) {
        if (currentTask==null) return;
        if (!currentTask.isCancelled() && !currentTask.isDone()) {
            context.showMessage("Previous task has been canceled");
        }
    }
    public View.OnClickListener getListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, buttonType.toString());
                MainActivity context = MainActivity.getFromContext(view);
                cancelPreviousTask(context);
                currentTask = MathService.perform(buttonType, context);
            }
        };
    }
}
