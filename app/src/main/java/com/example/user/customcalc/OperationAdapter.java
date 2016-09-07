package com.example.user.customcalc;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.user.customcalc.operations.Operation;
import com.example.user.customcalc.utils.UIUtils;

import java.math.BigInteger;

public class OperationAdapter extends BaseAdapter {
    private static final String TAG = MathService.class.getSimpleName();

    private OperationType[] buttonTypes;
    private Context context;

    public OperationAdapter(Context context, OperationType[] buttonTypes) {
        this.context = context;
        this.buttonTypes = buttonTypes;
    }

    @Override
    public int getCount() {
        return buttonTypes.length;
    }

    @Override
    public Object getItem(int position) {
        return buttonTypes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final OperationType buttonType = buttonTypes[position];
        final Operation operation;
        Button button;
        if (convertView == null) {
            button = new Button(context);
            operation = Operation.getButtonDescription(buttonType);
            assert operation != null;
            button.setText(operation.getLabel());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, buttonType.toString());
                    performOperation(view, operation);
                }
            });
        } else {
            button = (Button) convertView;
        }
        return button;
    }

    private void performOperation(View view, Operation operation) {
        MainActivity activity = (MainActivity) view.getContext();
        try {
            BigInteger[] arguments = activity.getInputArguments();
            BigInteger mod = activity.getMod();
            MathService.perform(arguments, mod, operation, activity);
        } catch (NumberFormatException e) {
            UIUtils.showMessage(activity, "Invalid arguments values");
        }
    }
}
