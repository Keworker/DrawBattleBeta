package com.samsung.drawbattle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainGameActivity extends Activity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private static byte gameStage;
    protected LinearLayout editTextLayout, toolbarLayout;
    protected EditText editMainGameText;
    //Red, green, blue, yellow, orange, black, brown, purple
    protected ImageButton r, g, b, y, o, sch, br, p,
            paintMode, lineMode, eraserMode, stickerAdd;
    protected SeekBar strokeWidth;
    protected com.samsung.drawbattle.KeworkerCanvas canvas;

    Button news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        editTextLayout = findViewById(R.id.editTextLayout);
        toolbarLayout = findViewById(R.id.toolbarLayout);
        canvas = findViewById(R.id.canvas);
        r = findViewById(R.id.r);
        r.setOnClickListener(this);
        g = findViewById(R.id.g);
        g.setOnClickListener(this);
        b = findViewById(R.id.b);
        b.setOnClickListener(this);
        y = findViewById(R.id.y);
        y.setOnClickListener(this);
        o = findViewById(R.id.o);
        o.setOnClickListener(this);
        sch = findViewById(R.id.sch);
        sch.setOnClickListener(this);
        br = findViewById(R.id.br);
        br.setOnClickListener(this);
        p = findViewById(R.id.p);
        p.setOnClickListener(this);
        paintMode = findViewById(R.id.paintMode);
        paintMode.setOnClickListener(this);
        lineMode = findViewById(R.id.lineMode);
        lineMode.setOnClickListener(this);
        eraserMode = findViewById(R.id.eraserMode);
        eraserMode.setOnClickListener(this);
        stickerAdd = findViewById(R.id.stickerAdd);
        stickerAdd.setOnClickListener(this);
        strokeWidth = findViewById(R.id.strokeWidth);
        strokeWidth.setOnSeekBarChangeListener(this);
        editMainGameText = findViewById(R.id.editMainGameText);

        //Временно переход по кнопке New, но потом будет по таймеру
        news = findViewById(R.id.news);
        news.setOnClickListener(this);
    }

    protected void onGameStageUpdate() {
        gameStage++;
        switch (gameStage) {
            case 2:
            case 4:
            case 6:{
                Log.d("My", "TextInput");
                editTextLayout.setVisibility(View.VISIBLE);
                toolbarLayout.setVisibility(View.GONE);
                break;
            }
            case 1:
            case 3:
            case 5:{
                Log.d("My", "Toolbar");
                editTextLayout.setVisibility(View.GONE);
                toolbarLayout.setVisibility(View.VISIBLE);
                break;
            }
            default:{
                gameStage = 7;
            }
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
                    catch (Exception exception) {
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
                    catch (Exception exception) {
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
                    catch (Exception exception) {
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
                    catch (Exception exception) {
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
                    catch (Exception exception) {
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
                    catch (Exception exception) {
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
                    catch (Exception exception) {
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
                    catch (Exception exception) {
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
            case R.id.stickerAdd:{

                break;
            }
            default:{
                onGameStageUpdate();
            }
        }
    }

    public static byte getGameStage() {
        return gameStage;
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
                    catch (Exception exception) {
                        Log.d("Exceptions", "Can not set this stroke width for paint");
                    }
                }
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        //We must not do something on back pressed
    }
}