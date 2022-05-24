package com.samsung.drawbattle.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.samsung.drawbattle.R;

public class EditTextFragment extends Fragment {
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_text, container, false);
        editText = view.findViewById(R.id.editTextFR);
        editText.setText("");
        return view;
    }

    public String getText() {
        return editText.getText().toString().length() > 1 ?
                editText.getText().toString() : "Nothing to write";
    }

    public void deleteText() {
//        editText.setText("");
    }
}