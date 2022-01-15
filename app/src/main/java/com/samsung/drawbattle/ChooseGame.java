package com.samsung.drawbattle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ChooseGame extends Activity implements View.OnClickListener {
    Button goToMainGame, goToDrawTournament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        goToMainGame = findViewById(R.id.goToMainGame);
        goToMainGame.setOnClickListener(this);
        goToDrawTournament = findViewById(R.id.goToDrawTournament);
        goToDrawTournament.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goToMainGame: {
                Intent intent = new Intent(this, MainGameSettings.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
            case R.id.goToDrawTournament: {
                Intent intent = new Intent(this, DrawTournamentSettings.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            }
        }
    }
}