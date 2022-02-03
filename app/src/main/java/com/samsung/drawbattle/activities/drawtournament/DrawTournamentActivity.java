package com.samsung.drawbattle.activities.drawtournament;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.samsung.drawbattle.classes.ImageRes;
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
    protected ImageRes rRes, gRes, bRes, yRes, oRes, schRes, brRes, pRes,
            paintModeRes, lineModeRes, eraserModeRes;
    public float screenWidth, screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tournament = true;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        setContentView(R.layout.activity_draw_tournament);
        canvas = findViewById(R.id.canvas);
        onCreateSetter();
    }

    protected void onCreateSetter() {
        question = findViewById(R.id.question);
        toolbarLayout = findViewById(R.id.toolbarLayout);
        gradeLayout = findViewById(R.id.gradeLayout);
        st1 = findButtonAndSetOnClick(st1, R.id.st1);
        st2 = findButtonAndSetOnClick(st2, R.id.st2);
        st3 = findButtonAndSetOnClick(st3, R.id.st3);
        st4 = findButtonAndSetOnClick(st4, R.id.st4);
        st5 = findButtonAndSetOnClick(st5, R.id.st5);
        st6 = findButtonAndSetOnClick(st6, R.id.st6);
        st7 = findButtonAndSetOnClick(st7, R.id.st7);
        st8 = findButtonAndSetOnClick(st8, R.id.st8);
        st9 = findButtonAndSetOnClick(st9, R.id.st9);
        s10 = findButtonAndSetOnClick(s10, R.id.s10);
        r = findButtonAndSetOnClick(r, R.id.r);
        g = findButtonAndSetOnClick(g, R.id.g);
        b = findButtonAndSetOnClick(b, R.id.b);
        y = findButtonAndSetOnClick(y, R.id.y);
        o = findButtonAndSetOnClick(o, R.id.o);
        sch = findButtonAndSetOnClick(sch, R.id.sch);
        br = findButtonAndSetOnClick(br, R.id.br);
        p = findButtonAndSetOnClick(p, R.id.p);
        paintMode = findButtonAndSetOnClick(paintMode, R.id.paintMode);
        lineMode = findButtonAndSetOnClick(lineMode, R.id.lineMode);
        eraserMode = findButtonAndSetOnClick(eraserMode, R.id.eraserMode);

        lineModeRes = new ImageRes(R.drawable.line, lineMode,
                (screenWidth / 3) / 8 - 0.1f, (screenWidth / 3) / 8 - 0.1f);
        eraserModeRes = new ImageRes(R.drawable.rubber, eraserMode,
                (screenWidth / 3) / 8 - 0.1f, (screenWidth / 3) / 8 - 0.1f);

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

    private Button findButtonAndSetOnClick(Button button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
        return button;
    }

    private ImageButton findButtonAndSetOnClick(ImageButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
        return button;
    }

    @Override
    public void onBackPressed() {
        canvas.back();
    }

    public static boolean isTournament() {
        return tournament;
    }
}