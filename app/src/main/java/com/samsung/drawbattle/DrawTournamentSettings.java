package com.samsung.drawbattle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DrawTournamentSettings extends Activity implements View.OnClickListener {
    protected Button randomButton, friendButton;
    private static boolean friendsMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tournament_settings);
        randomButton = findViewById(R.id.randomButton);
        randomButton.setOnClickListener(this);
        friendButton = findViewById(R.id.friendButton);
        friendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.randomButton:{
                friendsMode = false;
                Intent intent = new Intent(this, DrawTournamentRoom.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
            case R.id.friendButton:{
                friendsMode = true;
                Intent intent = new Intent(this, DrawTournamentRoom.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
        }
    }

    public static boolean getIfFriends() {
        return friendsMode;
    }
}