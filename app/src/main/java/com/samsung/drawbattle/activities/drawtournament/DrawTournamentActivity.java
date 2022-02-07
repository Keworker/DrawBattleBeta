package com.samsung.drawbattle.activities.drawtournament;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
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
    protected final int SECONDS_FOR_ROUND = 45;
    //Red, green, blue, yellow, orange, black, brown, purple
    protected ImageButton r, g, b, y, o, sch, br, p,
            paintMode, lineMode, eraserMode;
    protected Button timerView;
    protected TextView question;
    protected SeekBar strokeWidth;
    protected KeworkerCanvas canvas;
    private static boolean tournament;
    protected int gameStage;
    protected LinearLayout textLayout, toolbarLayout;
    protected ImageRes rRes, gRes, bRes, yRes, oRes, schRes, brRes, pRes,
            paintModeRes, lineModeRes, eraserModeRes;
    public float screenWidth, screenHeight;
    protected float canvasHeight, canvasWidth,
            layoutHeight, layoutWidth,
            colorButtonSize, buttonSize;
    protected Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tournament);
        tournament = true;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        canvasWidth = screenWidth;
        layoutWidth = screenWidth;
        canvasHeight = screenWidth / 40.0f * 16.0f;
        layoutHeight = (screenHeight - canvasHeight) / 2.0f;
        buttonSize = layoutHeight - 5;
        colorButtonSize = buttonSize / 2.0f;
        timer = new Timer(SECONDS_FOR_ROUND);
        onCreateSetter();
    }

    protected void onCreateSetter() {
        canvas = findViewById(R.id.canvasDT);
        r = findButtonAndSetOnClick(r, R.id.rDT);
        g = findButtonAndSetOnClick(r, R.id.gDT);
        b = findButtonAndSetOnClick(r, R.id.bDT);
        y = findButtonAndSetOnClick(r, R.id.yDT);
        o = findButtonAndSetOnClick(r, R.id.oDT);
        sch = findButtonAndSetOnClick(r, R.id.schDT);
        br = findButtonAndSetOnClick(r, R.id.brDT);
        p = findButtonAndSetOnClick(r, R.id.pDT);
        paintMode = findButtonAndSetOnClick(paintMode, R.id.paintModeDT);
        lineMode = findButtonAndSetOnClick(lineMode, R.id.lineModeDT);
        eraserMode = findButtonAndSetOnClick(eraserMode, R.id.eraserModeDT);
        timerView = findButtonAndSetOnClick(timerView, R.id.timerViewDT);
        strokeWidth = findViewById(R.id.strokeWidthDT);
        strokeWidth.setOnSeekBarChangeListener(this);
        question = findViewById(R.id.questionDT);
        textLayout = findViewById(R.id.textLayoutDT);
        toolbarLayout = findViewById(R.id.toolbarLayoutDT);
        timerView.getLayoutParams().height = (int) buttonSize;
        timerView.getLayoutParams().width = (int) (buttonSize * 1.5f);
        rRes = new ImageRes(R.drawable.red, r,
                colorButtonSize, colorButtonSize);
        gRes = new ImageRes(R.drawable.green, g,
                colorButtonSize, colorButtonSize);
        bRes = new ImageRes(R.drawable.blue, b,
                colorButtonSize, colorButtonSize);
        yRes = new ImageRes(R.drawable.yellow, y,
                colorButtonSize, colorButtonSize);
        oRes = new ImageRes(R.drawable.orange, o,
                colorButtonSize, colorButtonSize);
        schRes = new ImageRes(R.drawable.black, sch,
                colorButtonSize, colorButtonSize);
        brRes = new ImageRes(R.drawable.brown, br,
                colorButtonSize, colorButtonSize);
        pRes = new ImageRes(R.drawable.purplre, p,
                colorButtonSize, colorButtonSize);
        paintModeRes = new ImageRes(R.drawable.brush, paintMode,
                buttonSize, buttonSize);
        lineModeRes = new ImageRes(R.drawable.line, lineMode,
                buttonSize, buttonSize);
        eraserModeRes = new ImageRes(R.drawable.rubber, eraserMode,
                buttonSize, buttonSize);
    }

    protected void onGameStageUpdate() {
        if (gameStage % 2 == 0) {

        }
        else if (gameStage < 7) {

        }
        else {
            gameStage = 0;
            tournament = false;
            Intent intent = new Intent(this, DrawTournamentResults.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            return;
        }
        timer.reload(SECONDS_FOR_ROUND);
        gameStage++;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rDT:{ //Red
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 255, 0, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.gDT:{ //Green
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 255, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.bDT:{ //Blue
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 0, 255);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.yDT:{ //Yellow
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 245, 220, 15);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.oDT:{ //Orange
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 250, 140, 15);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.schDT:{ //Black (schwarz)
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 0, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.brDT:{ //Brown
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 100, 70, 30);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.pDT:{ //Purple
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 155, 30, 245);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.paintModeDT:{
                canvas.setPaintMode();
                break;
            }
            case R.id.lineModeDT:{
                canvas.setLineMode();
                break;
            }
            case R.id.eraserModeDT:{
                canvas.setEraserMode();
                break;
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
            case R.id.strokeWidthDT:{
                while (true) {
                    try {
                        canvas.setWidth((float) seekBar.getProgress());
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
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

    public class Timer {
        private int seconds;
        private MyAsyncTask asyncTask;

        public Timer(int seconds) {
            this.seconds = seconds;
            this.asyncTask = new MyAsyncTask();
            asyncTask.execute();
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public void reload(int seconds) {
            this.seconds = seconds;
            asyncTask.cancel(true);
            asyncTask = new MyAsyncTask();
            asyncTask.execute();
        }

        protected void dropTime() {
            this.seconds = 5;
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
                    try {
                        Thread.sleep(999);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(seconds);
                }
                return "All right";
            }

            @Override
            protected void onPostExecute(String unused) {
                super.onPostExecute(unused);
                timerView.setText(R.string.timeStop);
                onGameStageUpdate();
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                timerView.setText(((values[0] / 60 < 10) ?
                        ("0" + Integer.toString(values[0] / 60)) :
                        Integer.toString(values[0] / 60) + ":") + ":" +
                        ((values[0] % 60 < 10) ? ("0" + Integer.toString(values[0] % 60))
                                : Integer.toString(values[0] % 60))); //Time like 09:35
            }
        }
    }
}