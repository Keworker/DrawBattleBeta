package com.samsung.drawbattle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends Activity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener {
    protected ImageButton off;
    protected Button back;
    protected RadioGroup theme;
    protected TextView aboutDev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        off = findViewById(R.id.off);
        off.setOnClickListener(this);
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
            case R.id.off:{
                //Добавить окно подтверждения
                System.exit(0);
                break;
            }
            case R.id.back:{
                Intent intent = new Intent(this, Menu.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}