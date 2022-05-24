package com.samsung.drawbattle.activities.maingame;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;


import androidx.annotation.NonNull;

import com.samsung.drawbattle.activities.Menu;
import com.samsung.drawbattle.activities.dialogfragments.ShowTextDF;
import com.samsung.drawbattle.classes.KeworkerCanvas;
import com.samsung.drawbattle.R;
import com.samsung.drawbattle.fragments.EditTextFragment;
import com.samsung.drawbattle.fragments.ToolbarFragmentMG;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainGameActivity extends Activity implements View.OnClickListener {
    public static MainGameActivity game;
    protected final int SECONDS_FOR_ROUND = 85;
    private static byte gameStage;
    protected LinearLayout fullLayout;
    public KeworkerCanvas canvas;
    protected Button timerView;
    public float screenWidth, screenHeight;
    public static float normalButtonSize, normalLayoutWidth, normalFragmentHeight, normalCanvasHeight;
    protected LinearLayout mainGameLeftSide, mainGameRightSide;
    protected FrameLayout frameLayout;
    protected EditTextFragment editTextFragment;
    protected ToolbarFragmentMG toolbarFragment;
    protected FragmentTransaction ft;
    protected Handler handler;
    public final int HANDLER_FOR_GAME_ST_UPDATE = 1;
    String etRes;
    Timer timer;
    protected ImageButton show;

    ImageButton news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.arg1) {
                    case HANDLER_FOR_GAME_ST_UPDATE: {
                        onGameStageUpdate();
                        break;
                    }
                }
            }
        };
        show = findViewById(R.id.show);
        show.setClickable(false);
        game = this;
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

    public void onGameStageUpdate() {
        if (gameStage < 1) { //< 6
            if (gameStage % 2 == 0) {
                show.setVisibility(View.GONE);
                canvas.setVisibility(View.VISIBLE);
                canvas.setFrozen(false);
                ShowTextDF df = new ShowTextDF();
                df.setText(etRes);
                FragmentManager manager = getFragmentManager();
                df.show(manager, "ShowMeText");
                addFragment(toolbarFragment);
                gameStage++;
                timer.reload(game.SECONDS_FOR_ROUND);
            }
            else {
                canvas.setVisibility(View.GONE);
                show.setVisibility(View.VISIBLE);
                show.setImageBitmap(canvas.getBitmapFromServ());
                canvas.reset();
                addFragment(editTextFragment);
                gameStage++;
                timer.reload(game.SECONDS_FOR_ROUND);
            }
        }
        else {
            gameStage = -1;
            Intent intent = new Intent(this, Menu.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
        public void onClick(View view) {
            switch (view.getId()) {
            default:
//                Message m = new Message();
//                m.arg1 = 1;
//                MainGameActivity.game.handler.sendMessage(m);
        }
    }

    public String getText() {
        return editTextFragment.getText();
    }

    private void addFragment(Fragment fragment) {
        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMG, fragment);
        ft.addToBackStack(null);
        ft.commit();
        editTextFragment.deleteText();
    }

    public static byte getGameStage() {
        return gameStage;
    }

    public void setEtRes(String etRes) {
        this.etRes = etRes;
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
                        Thread.sleep(1000);
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
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                timerView.setText(((values[0] / 60 < 10) ?
                        ("0" + values[0] / 60) :
                        Integer.toString(values[0] / 60)) +
                        ":" +
                        ((values[0] % 60 < 10) ?
                                ("0" + values[0] % 60) :
                                values[0] % 60)); //Time like 09:35
            }
        }
    }
}
