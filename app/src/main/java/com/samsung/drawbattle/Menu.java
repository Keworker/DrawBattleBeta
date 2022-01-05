package com.samsung.drawbattle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity implements View.OnClickListener {
    protected Button settings, start, saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(this);
        start = findViewById(R.id.start);
        start.setOnClickListener(this);
        saved = findViewById(R.id.saved);
        saved.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings:{
                Intent intent = new Intent(this, Settings.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
            case R.id.start:{
                Intent intent = new Intent(this, MainGameSettings.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
            case R.id.saved:{
                Intent intent = new Intent(this, Saved.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
        }
    }
}