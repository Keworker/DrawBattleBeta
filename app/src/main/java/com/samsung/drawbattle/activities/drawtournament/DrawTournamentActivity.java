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

import com.samsung.drawbattle.classes.ImageRes;
import com.samsung.drawbattle.classes.KeworkerCanvas;
import com.samsung.drawbattle.R;
import com.samsung.drawbattle.fragments.ToolbarFragmentDT;

import org.w3c.dom.Text;

public class DrawTournamentActivity extends Activity implements View.OnClickListener {
    private static boolean tournament;
    protected LinearLayout onL, underL;
    protected Button timerView;
    protected TextView task;
    public static KeworkerCanvas canvas;
    protected FrameLayout fl;
    protected float screenWidth, screenHeight;
    public static int toolbarHeight, toolbarWidth;
    FragmentTransaction ft;
    ToolbarFragmentDT toolbar;

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
        addFragment(toolbar);
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

    private void addFragment(Fragment fragment) {
        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentDT, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        tournament = false;
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

    }

    public static boolean isTournament() {
        return tournament;
    }
}