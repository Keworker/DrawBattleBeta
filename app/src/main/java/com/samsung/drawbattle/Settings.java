package com.samsung.drawbattle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends Activity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener {
    protected Button back;
    protected RadioGroup theme;
    protected TextView aboutDev;
    protected boolean sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        theme = findViewById(R.id.theme);
        theme.setOnCheckedChangeListener(this);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        aboutDev = findViewById(R.id.aboutDev);
        if (aboutDev != null) {
            aboutDev.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:{
                Intent intent = new Intent(this, Menu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.darkTheme:{
                createDialog(getString(R.string.app_name), getString(R.string.question));
                if (sure) {

                }
                break;
            }
            case R.id.lightTheme:{
                createDialog(getString(R.string.app_name), getString(R.string.question));
                break;
            }
        }
    }

    private void createDialog(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton(getText(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sure = false;
                    }
                });
        builder.setPositiveButton(getText(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sure = true;
                    }
                });
        builder.show();
    }
}