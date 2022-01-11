package com.samsung.drawbattle;

import android.app.Activity;
import android.os.Bundle;

public class DrawTournamentActivity extends Activity {
    protected KeworkerCanvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tournament);
        canvas = findViewById(R.id.canvas);
    }
}