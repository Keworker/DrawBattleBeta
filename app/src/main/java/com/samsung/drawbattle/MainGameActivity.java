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
            paintMode, lineMode, eraserMode, stickerAdd, back;
    protected SeekBar strokeWidth;
    protected KeworkerCanvas canvas;

    Button news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        editTextLayout = findViewById(R.id.editTextLayout);
        toolbarLayout = findViewById(R.id.toolbarLayout);
        canvas = findViewById(R.id.canvas);
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
        findButtonAndSetOnClick(stickerAdd, R.id.stickerAdd);
        strokeWidth = findViewById(R.id.strokeWidth);
        strokeWidth.setOnSeekBarChangeListener(this);
        findButtonAndSetOnClick(back, R.id.back);
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
                editTextLayout.setVisibility(View.VISIBLE);
                toolbarLayout.setVisibility(View.GONE);
                break;
            }
            case 1:
            case 3:
            case 5:{
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
            case R.id.stickerAdd:{

                break;
            }
            case R.id.back:{
                canvas.back();
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
        //We must not do something on back pressed
    }
}