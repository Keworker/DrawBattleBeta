package com.samsung.drawbattle.activities.maingame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.samsung.drawbattle.activities.dialogfragments.ShowTextDF;
import com.samsung.drawbattle.classes.ImageRes;
import com.samsung.drawbattle.classes.KeworkerCanvas;
import com.samsung.drawbattle.R;
import com.samsung.drawbattle.fragments.EditTextFragment;
import com.samsung.drawbattle.fragments.ToolbarFragmentMG;

public class MainGameActivity extends FragmentActivity implements View.OnClickListener {
    protected final int SECONDS_FOR_ROUND = 90;
    private static byte gameStage;
    protected LinearLayout fullLayout;
    public static KeworkerCanvas canvas;
    protected Button timerView;
    protected Timer timer;
    protected ImageRes rRes, gRes, bRes, yRes, oRes, schRes, brRes, pRes,
            paintModeRes, lineModeRes, eraserModeRes, stickerAddRes;
    public float screenWidth, screenHeight;
    public static float normalButtonSize, normalLayoutWidth, normalFragmentHeight, normalCanvasHeight;
    protected LinearLayout mainGameLeftSide, mainGameRightSide;
    protected FrameLayout frameLayout;
    protected EditTextFragment editTextFragment;
    protected ToolbarFragmentMG toolbarFragment;
    protected FragmentTransaction ft;
    String etRes;

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
        canvas = findViewById(R.id.canvas);
        normalLayoutWidth = screenWidth / 5 * 4;
        normalCanvasHeight = normalLayoutWidth / 16.0f * 9.0f;
        normalFragmentHeight = screenHeight - normalCanvasHeight;
        normalButtonSize = (normalFragmentHeight - 5.0f);
        timer = new Timer(SECONDS_FOR_ROUND);
        timer.setSeconds(SECONDS_FOR_ROUND);
        timer.reload(SECONDS_FOR_ROUND);
        gameStage = 0;
        onCreateSetter();
        editTextFragment = new EditTextFragment();
        toolbarFragment = new ToolbarFragmentMG();
        addFragment(editTextFragment);

        news = findViewById(R.id.news);
        news.setOnClickListener(this);
        //Временно переход по кнопке New, но потом будет по таймеру
    }

    protected void onCreateSetter() {
        canvas = findViewById(R.id.canvas);
        frameLayout = findViewById(R.id.frameLayoutMG);
        fullLayout = findViewById(R.id.fullLayout);
        timerView = findViewById(R.id.timerView);
        mainGameLeftSide = findViewById(R.id.mainGameLeftSide);
        mainGameRightSide = findViewById(R.id.mainGameRightSide);
        mainGameLeftSide.getLayoutParams().width = (int) (screenWidth / 5.0f);
        mainGameRightSide.getLayoutParams().width = (int) normalLayoutWidth;
        frameLayout.getLayoutParams().width = (int) normalLayoutWidth;
        frameLayout.getLayoutParams().height = (int) normalFragmentHeight;
        canvas.getLayoutParams().width = (int) normalLayoutWidth;
        canvas.getLayoutParams().height = (int) normalCanvasHeight;
        timerView.setClickable(false);
        fullLayout.getLayoutParams().height = (int) (normalFragmentHeight);
    }

    protected void onGameStageUpdate() {
        if (gameStage < 5) {
            if (gameStage % 2 == 0) {
                etRes = editTextFragment.getText();
                addFragment(toolbarFragment);
                ShowTextDF df = new ShowTextDF();
                df.setText(etRes);
                FragmentManager manager = getFragmentManager();
                df.show(manager, "ShowMeText");
            }
            else {
                addFragment(editTextFragment);
            }
        }
        else {
            gameStage = -1;
            Intent intent = new Intent(this, MainGameResults.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return;
        }
        timer.reload(SECONDS_FOR_ROUND);
        gameStage++;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                timer.dropTime();
                onGameStageUpdate();
        }
    }

    private void addFragment(Fragment fragment) {
        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMG, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static byte getGameStage() {
        return gameStage;
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
                    catch (InterruptedException e) {}
                    publishProgress(seconds);
                }
                seconds = 15;
                while (seconds > 0) {
                    seconds--;
                    try {
                        Thread.sleep(999);
                    }
                    catch (InterruptedException e) {}
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
                        ("0" + values[0] / 60) :
                        values[0] / 60 + ":") +
                        ":" +
                        ((values[0] % 60 < 10) ?
                        ("0" + values[0] % 60) :
                        values[0] % 60)); //Time like 09:35
            }
        }
    }
}
