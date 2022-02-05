package com.samsung.drawbattle.activities.maingame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.samsung.drawbattle.classes.ImageRes;
import com.samsung.drawbattle.classes.KeworkerCanvas;
import com.samsung.drawbattle.R;
import com.samsung.drawbattle.fragments.ToolbarFragment;

public class MainGameActivity extends Activity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    protected final int SECONDS_FOR_ROUND = 90;
    private static byte gameStage;
    protected LinearLayout editTextLayout, toolbarLayout, fullLayout;
    protected EditText editMainGameText;
    //Red, green, blue, yellow, orange, black, brown, purple
    protected ImageButton r, g, b, y, o, sch, br, p, paintMode, lineMode, eraserMode, stickerAdd;
    protected SeekBar strokeWidth;
    protected KeworkerCanvas canvas;
    protected Button timerView;
    protected Timer timer;
    protected ImageRes rRes, gRes, bRes, yRes, oRes, schRes, brRes, pRes,
            paintModeRes, lineModeRes, eraserModeRes, stickerAddRes;
    public float screenWidth, screenHeight;
    private float normalButtonSize, normalLayoutWidth, normalToolbarHeight, normalCanvasHeight;
    protected LinearLayout mainGameLeftSide, mainGameRightSide;
    protected FragmentContainerView fcv;

    Button news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        normalLayoutWidth = screenWidth / 5 * 4;
        normalCanvasHeight = normalLayoutWidth / 16.0f * 9.0f;
        normalToolbarHeight = screenHeight - normalCanvasHeight;
        normalButtonSize = (normalToolbarHeight - 5.0f);
        timer = new Timer(SECONDS_FOR_ROUND);
        timer.setSeconds(SECONDS_FOR_ROUND);
        timer.reload(SECONDS_FOR_ROUND);
        gameStage = 0;
        onCreateSetter();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.fcvMG, ToolbarFragment.class, null).commit();

        news = findViewById(R.id.news);
        news.setOnClickListener(this);
        //Временно переход по кнопке New, но потом будет по таймеру
    }

    protected void onCreateSetter() {
        canvas = findViewById(R.id.canvas);
        fcv = findViewById(R.id.fcvMG);
        editTextLayout = findViewById(R.id.editTextLayout);
        toolbarLayout = findViewById(R.id.toolbarLayout);
        fullLayout = findViewById(R.id.fullLayout);
        timerView = findViewById(R.id.timerView);
        mainGameLeftSide = findViewById(R.id.mainGameLeftSide);
        mainGameRightSide = findViewById(R.id.mainGameRightSide);
        r = findButtonAndSetOnClick(r, R.id.r);
        g = findButtonAndSetOnClick(g, R.id.g);
        b = findButtonAndSetOnClick(b, R.id.b);
        y = findButtonAndSetOnClick(y, R.id.y);
        o = findButtonAndSetOnClick(o, R.id.o);
        sch = findButtonAndSetOnClick(sch, R.id.sch);
        br = findButtonAndSetOnClick(br, R.id.br);
        p = findButtonAndSetOnClick(p, R.id.p);
        strokeWidth = findViewById(R.id.strokeWidth);
        editMainGameText = findViewById(R.id.editMainGameText);
        editMainGameText.setVisibility(View.VISIBLE);
        toolbarLayout.setVisibility(View.GONE);
        mainGameLeftSide.getLayoutParams().width = (int) (screenWidth / 5.0f);
        paintMode = findButtonAndSetOnClick(paintMode, R.id.paintMode);
        lineMode = findButtonAndSetOnClick(lineMode, R.id.lineMode);
        eraserMode = findButtonAndSetOnClick(eraserMode, R.id.eraserMode);
//        stickerAdd = findButtonAndSetOnClick(stickerAdd, R.id.stickerAdd);
        mainGameRightSide.getLayoutParams().width = (int) normalLayoutWidth;
        toolbarLayout.getLayoutParams().width = (int) normalLayoutWidth;
        editTextLayout.getLayoutParams().width = (int) normalLayoutWidth;
        toolbarLayout.getLayoutParams().height = 0;
        editTextLayout.getLayoutParams().height = (int) normalToolbarHeight;
        canvas.getLayoutParams().width = (int) normalLayoutWidth;
        canvas.getLayoutParams().height = (int) normalCanvasHeight;
        timerView.setClickable(false);
        fullLayout.getLayoutParams().height = (int) normalButtonSize + (int) (normalToolbarHeight);
        rRes = new ImageRes(R.drawable.red, r, normalButtonSize / 2.0f,
                normalButtonSize / 2.0f);
        gRes = new ImageRes(R.drawable.green, g, normalButtonSize / 2.0f,
                normalButtonSize / 2.0f);
        bRes = new ImageRes(R.drawable.blue, b, normalButtonSize / 2.0f,
                normalButtonSize / 2.0f);
        yRes = new ImageRes(R.drawable.yellow, y, normalButtonSize / 2.0f,
                normalButtonSize / 2.0f);
        oRes = new ImageRes(R.drawable.orange, o, normalButtonSize / 2.0f,
                normalButtonSize / 2.0f);
        schRes = new ImageRes(R.drawable.black, sch, normalButtonSize / 2.0f,
                normalButtonSize / 2.0f);
        brRes = new ImageRes(R.drawable.brown, br, normalButtonSize / 2.0f,
                normalButtonSize / 2.0f);
        pRes = new ImageRes(R.drawable.purplre, p, normalButtonSize / 2.0f,
                normalButtonSize / 2.0f);
        paintModeRes = new ImageRes(R.drawable.brush, paintMode, normalButtonSize,
                normalButtonSize);
        lineModeRes = new ImageRes(R.drawable.line, lineMode, normalButtonSize,
                normalButtonSize);
        eraserModeRes = new ImageRes(R.drawable.rubber, eraserMode, normalButtonSize,
                normalButtonSize);
//        stickerAddRes = new ImageRes(R.drawable.stiker, stickerAdd, normalButtonSize,
//                normalButtonSize);
        strokeWidth.setOnSeekBarChangeListener(this);
    }

    protected void onGameStageUpdate() {
        if (gameStage % 2 == 0) {
//            editTextLayout.setVisibility(View.GONE);
//            toolbarLayout.setVisibility(View.VISIBLE);
        }
        else if (gameStage < 7) {
//            toolbarLayout.setVisibility(View.GONE);
//            editTextLayout.setVisibility(View.VISIBLE);
        }
        else {
            gameStage = 0;
//            toolbarLayout.setVisibility(View.GONE);
//            editTextLayout.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, MainGameResults.class);
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
            case R.id.r: { //Red
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 255, 0, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        break;
                    }
                }
                break;
            }
            case R.id.g: { //Green
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 255, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        break;
                    }
                }
                break;
            }
            case R.id.b: { //Blue
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 0, 255);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        break;
                    }
                }
                break;
            }
            case R.id.y: { //Yellow
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 245, 220, 15);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        break;
                    }
                }
                break;
            }
            case R.id.o: { //Orange
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 250, 140, 15);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        break;
                    }
                }
                break;
            }
            case R.id.sch: { //Black (schwarz)
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 0, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        break;
                    }
                }
                break;
            }
            case R.id.br: { //Brown
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 100, 70, 30);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        break;
                    }
                }
                break;
            }
            case R.id.p: { //Purple
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 155, 30, 245);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        break;
                    }
                }
                break;
            }
            case R.id.paintMode: {
                canvas.setPaintMode();
                break;
            }
            case R.id.lineMode: {
                canvas.setLineMode();
                break;
            }
            case R.id.eraserMode: {
                canvas.setEraserMode();
                break;
            }
//            case R.id.stickerAdd: {

//                break;
//            }
            default:
                timer.dropTime();
        }
    }

    public static byte getGameStage() {
        return gameStage;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.strokeWidth: {
                while (true) {
                    try {
                        canvas.setWidth((float) seekBar.getProgress());
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {
                        break;
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

        public void end() {
            seconds = -1;
            timerView.setText(R.string.timeStop);
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
                seconds = 15;
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
