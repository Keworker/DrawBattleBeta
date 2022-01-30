package com.samsung.drawbattle;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.concurrent.TimeUnit;

public class MainGameActivity extends Activity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    protected final int SECONDS_FOR_ROUND = 90;
    private static byte gameStage;
    protected LinearLayout editTextLayout, toolbarLayout;
    protected EditText editMainGameText;
    //Red, green, blue, yellow, orange, black, brown, purple
    protected ImageButton r, g, b, y, o, sch, br, p,
            paintMode, lineMode, eraserMode, stickerAdd ;
    protected SeekBar strokeWidth;
    protected KeworkerCanvas canvas;
    protected Button timerView;
    protected Timer timer;

    Button news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        timer = new Timer(SECONDS_FOR_ROUND);
        timer.setSeconds(SECONDS_FOR_ROUND);
        timer.reload(SECONDS_FOR_ROUND);
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
        findButtonAndSetOnClick(timerView, R.id.timerView);
        strokeWidth = findViewById(R.id.strokeWidth);
        strokeWidth.setOnSeekBarChangeListener(this);
        editMainGameText = findViewById(R.id.editMainGameText);

        //Временно переход по кнопке New, но потом будет по таймеру
        news = findViewById(R.id.news);
        news.setOnClickListener(this);
    }

    protected void onGameStageUpdate() {
        if (gameStage % 2 == 0) {

            toolbarLayout.setVisibility(View.GONE);
            editTextLayout.setVisibility(View.VISIBLE);
            gameStage++;
        }
        else if (gameStage < 6) {

            editTextLayout.setVisibility(View.GONE);
            toolbarLayout.setVisibility(View.VISIBLE);
            gameStage++;
        }
        else {

            gameStage = 0;
            Intent intent = new Intent(this, MainGameResults.class);
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

    public class Timer  {
        private int seconds;

        public Timer(int seconds) {
            this.seconds = seconds;
            this.asyncTask = new MyAsyncTask();
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        private MyAsyncTask asyncTask;

        public void reload(int seconds) {
            this.seconds = seconds;
            asyncTask.execute();
        }

        public void end() {

        }

        private class MyAsyncTask extends AsyncTask<Void, Integer, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                while (seconds > 0) {
                    seconds--;
                    publishProgress(seconds);
                }
                return "All right";
            }

            @Override
            protected void onPostExecute(String unused) {
                super.onPostExecute(unused);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                timerView.setText((values[0] / 60 < 10) ?
                        ("0" + Integer.toString(values[0] / 60))
                        : Integer.toString(values[0] / 60) + ":"
                        + ((values[0] % 60 < 10) ? ("0" + Integer.toString(values[0] % 60))
                        : Integer.toString(values[0] % 60))); //Time like 09:35
            }
        }
    }

}