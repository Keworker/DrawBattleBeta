package com.samsung.drawbattle.activities.drawtournament;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.samsung.drawbattle.classes.KeworkerCanvas;
import com.samsung.drawbattle.R;

public class DrawTournamentActivity extends Activity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    //Red, green, blue, yellow, orange, black, brown, purple
    protected ImageButton r, g, b, y, o, sch, br, p,
            paintMode, lineMode, eraserMode;
    protected TextView question;
    protected SeekBar strokeWidth;
    protected KeworkerCanvas canvas;
    private static boolean tournament;
    protected int gameStage;
    protected LinearLayout toolbarLayout, gradeLayout;
    //1 star, 2 stars, 3 stars, ..., 10 stars
    protected ImageButton st1, st2, st3, st4,st5, st6, st7, st8, st9, s10;
    private boolean isClickOnStar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tournament = true;
        setContentView(R.layout.activity_draw_tournament);
        canvas = findViewById(R.id.canvas);
        question = findViewById(R.id.question);
        toolbarLayout = findViewById(R.id.toolbarLayout);
        gradeLayout = findViewById(R.id.gradeLayout);
        findButtonAndSetOnClick(st1, R.id.st1);
        findButtonAndSetOnClick(st2, R.id.st2);
        findButtonAndSetOnClick(st3, R.id.st3);
        findButtonAndSetOnClick(st4, R.id.st4);
        findButtonAndSetOnClick(st5, R.id.st5);
        findButtonAndSetOnClick(st6, R.id.st6);
        findButtonAndSetOnClick(st7, R.id.st7);
        findButtonAndSetOnClick(st8, R.id.st8);
        findButtonAndSetOnClick(st9, R.id.st9);
        findButtonAndSetOnClick(s10, R.id.s10);
        findButtonAndSetOnClick(r, R.id.r);
        findButtonAndSetOnClick(g, R.id.g);
        findButtonAndSetOnClick(b, R.id.b);
        findButtonAndSetOnClick(y, R.id.y);
        findButtonAndSetOnClick(o, R.id.o);
        findButtonAndSetOnClick(sch, R.id.sch);
        findButtonAndSetOnClick(br, R.id.br);
        findButtonAndSetOnClick(p, R.id.p);
        findButtonAndSetOnClick(paintMode, R.id.paintMode);
        findButtonAndSetOnClick(lineMode, R.id.lineMode);
        findButtonAndSetOnClick(eraserMode, R.id.eraserMode);
        strokeWidth = findViewById(R.id.strokeWidth);
        strokeWidth.setOnSeekBarChangeListener(this);
    }

    protected void onGameStageUpdate() {
        if (gameStage % 2 != 0) {

            gradeLayout.setVisibility(View.GONE);
            toolbarLayout.setVisibility(View.VISIBLE);
            gameStage++;
        }
        else if (gameStage < 6) {

            toolbarLayout.setVisibility(View.GONE);
            gradeLayout.setVisibility(View.VISIBLE);
            gameStage++;
        }
        else {
            Intent intent = new Intent(this, DrawTournamentResults.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.r:{ //Red
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 255, 0, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        Log.d("Exceptions", "Can not set this ARGB for paint");
                    }
                }
                break;
            }
            case R.id.g:{ //Green
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 255, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        Log.d("Exceptions", "Can not set this ARGB for paint");
                    }
                }
                break;
            }
            case R.id.b:{ //Blue
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 0, 255);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        Log.d("Exceptions", "Can not set this ARGB for paint");
                    }
                }
                break;
            }
            case R.id.y:{ //Yellow
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 245, 220, 15);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        Log.d("Exceptions", "Can not set this ARGB for paint");
                    }
                }
                break;
            }
            case R.id.o:{ //Orange
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 250, 140, 15);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        Log.d("Exceptions", "Can not set this ARGB for paint");
                    }
                }
                break;
            }
            case R.id.sch:{ //Black (schwarz)
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 0, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        Log.d("Exceptions", "Can not set this ARGB for paint");
                    }
                }
                break;
            }
            case R.id.br:{ //Brown
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 100, 70, 30);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        Log.d("Exceptions", "Can not set this ARGB for paint");
                    }
                }
                break;
            }
            case R.id.p:{ //Purple
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 155, 30, 245);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        Log.d("Exceptions", "Can not set this ARGB for paint");
                    }
                }
                break;
            }
            case R.id.paintMode:{
                canvas.setPaintMode();
                break;
            }
            case R.id.lineMode:{
                canvas.setLineMode();
                break;
            }
            case R.id.eraserMode:{
                canvas.setEraserMode();
                break;
            }
            case R.id.s10: {
                if (!isClickOnStar) {}
                //Set fullstar image
            }
            case R.id.st9: {

            }
            case R.id.st8: {
                //Set fullstar image
            }
            case R.id.st7: {

            }
            case R.id.st6: {

            }
            case R.id.st5: {
                //Set fullstar image
            }
            case R.id.st4: {

            }
            case R.id.st3: {

            }
            case R.id.st2: {
                //Set fullstar image
            }
            case R.id.st1: {

            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.strokeWidth:{
                while (true) {
                    try {
                        canvas.setWidth((float) seekBar.getProgress());
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        Log.d("Exceptions", "Can not set this stroke width for paint");
                    }
                }
                break;
            }
        }
    }

    private void findButtonAndSetOnClick(Button button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    private void findButtonAndSetOnClick(ImageButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        canvas.back();
    }

    public static boolean isTournament() {
        return tournament;
    }
}