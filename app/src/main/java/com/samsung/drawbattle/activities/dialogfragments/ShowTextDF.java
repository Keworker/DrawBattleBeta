package com.samsung.drawbattle.activities.dialogfragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.app.DialogFragment;

import com.samsung.drawbattle.R;

public class ShowTextDF extends DialogFragment implements View.OnClickListener {
    String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.show_text_df, null);
        TextView text = view.findViewById(R.id.textST);
        text.setText(this.text);
        Button accept = view.findViewById(R.id.acceptST);
        accept.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.acceptST: {
                this.dismiss();
                break;
            }
        }
    }
}