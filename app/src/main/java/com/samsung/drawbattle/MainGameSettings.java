package com.samsung.drawbattle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainGameSettings extends Activity implements View.OnClickListener {
    protected Button rBut, fBut;
    private static boolean friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_settings);
        rBut = findViewById(R.id.rBut);
        rBut.setOnClickListener(this);
        fBut = findViewById(R.id.fBut);
        fBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rBut:{
                friends = false;
                Intent intent = new Intent(this, MainGameRoom.class);
                startActivity(intent);
                break;
            }
            case R.id.fBut:{
                friends = true;
                Intent intent = new Intent(this, MainGameRoom.class);
                startActivity(intent);
                break;
            }
        }
    }

    public static boolean getIfFriends() {
        return friends;
    }
}