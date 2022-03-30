package com.samsung.drawbattle.activities.drawtournament;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.samsung.drawbattle.activities.maingame.MainGameResults;
import com.samsung.drawbattle.classes.ImageRes;
import com.samsung.drawbattle.classes.KeworkerCanvas;
import com.samsung.drawbattle.R;
import com.samsung.drawbattle.fragments.RatingFragmentDT;
import com.samsung.drawbattle.fragments.ToolbarFragmentDT;

import org.w3c.dom.Text;

public class DrawTournamentActivity extends Activity implements View.OnClickListener {
    private static byte gameStage;
    private static boolean tournament;
    protected LinearLayout onL, underL;
    protected Button timerView;
    protected TextView task;
    public static KeworkerCanvas canvas;
    protected FrameLayout fl;
    protected float screenWidth, screenHeight;
    public static int toolbarHeight, toolbarWidth;
    private FragmentTransaction ft;
    private ToolbarFragmentDT toolbar;
    private RatingFragmentDT rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tournament);
        tournament = true;
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        screenWidth = size.x; screenHeight = size.y;
        onCreateSetter();
        toolbar = new ToolbarFragmentDT();
        rate = new RatingFragmentDT();
        gameStage = 0;
        addFragment(toolbar);

        timerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGameStageUpdate();
            }
        });
    }

    private void onCreateSetter() {
        onL = findViewById(R.id.onLayoutDT);
        timerView = findViewById(R.id.timerDT);
        task = findViewById(R.id.taskDT);
        underL = findViewById(R.id.underLayoutDT);
        canvas = findViewById(R.id.canvasDT);
        fl = findViewById(R.id.fragmentDT);
        onL.getLayoutParams().height = (int) (screenHeight / 5f);
        timerView.getLayoutParams().height = (int) (screenHeight / 5f / 3f * 2);
        task.getLayoutParams().height = (int) (screenHeight / 5f);
        timerView.getLayoutParams().width = (int) (screenWidth / 6f * 2);
        task.getLayoutParams().width = (int) (screenWidth / 6f * 4);
        toolbarHeight = (int) (screenHeight / 5f * 4);
        underL.getLayoutParams().height = toolbarHeight;
        canvas.getLayoutParams().height = toolbarHeight;
        fl.getLayoutParams().height = toolbarHeight;
        canvas.getLayoutParams().width = (int) (toolbarHeight / 9f * 16);
        toolbarWidth = (int) (screenWidth - canvas.getLayoutParams().width);
        fl.getLayoutParams().width = toolbarWidth;
    }

    protected void onGameStageUpdate() {
        if (gameStage % 2 == 0) {
            addFragment(rate);
        }
        else if (gameStage < 6) {
            addFragment(toolbar);
        }
        else {
            gameStage = -1;
            Intent intent = new Intent(this, DrawTournamentResults.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        gameStage++;
    }

    private void addFragment(Fragment fragment) {
        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentDT, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static byte getGameStage() {
        return gameStage;
    }

    @Override
    protected void onDestroy() {
        tournament = false;
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        canvas.back();
    }

    public static boolean isTournament() {
        return tournament;
    }
}