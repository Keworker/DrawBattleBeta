package com.samsung.drawbattle.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.samsung.drawbattle.R;
import com.samsung.drawbattle.activities.drawtournament.DrawTournamentActivity;
import com.samsung.drawbattle.classes.ImageRes;
import com.samsung.drawbattle.classes.KeworkerCanvas;

public class ToolbarFragmentDT extends Fragment implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {
    LinearLayout sts, leftSt, rightSt, l;
    //Red, green, blue, yellow, orange, black, brown, purple
    protected ImageButton r, g, b, y, o, sch, br, p,
            paintMode, lineMode, eraserMode;
    protected ImageRes rRes, gRes, bRes, yRes, oRes, schRes, brRes, pRes,
            paintModeRes, lineModeRes, eraserModeRes;
    protected SeekBar strokeWidth;
    protected static KeworkerCanvas canvas;
    protected int toolbarHeight, toolbarWidth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toolbar_dt, container, false);
        l = view.findViewById(R.id.l);
        sts = view.findViewById(R.id.stsDTF);
        leftSt = view.findViewById(R.id.leftStDTF);
        rightSt = view.findViewById(R.id.rightStDTF);
        r = view.findViewById(R.id.rDTF);
        g = view.findViewById(R.id.gDTF);
        b = view.findViewById(R.id.bDTF);
        y = view.findViewById(R.id.yDTF);
        o = view.findViewById(R.id.oDTF);
        sch = view.findViewById(R.id.schDTF);
        br = view.findViewById(R.id.brDTF);
        p = view.findViewById(R.id.pDTF);
        paintMode = view.findViewById(R.id.paintModeDTF);
        lineMode = view.findViewById(R.id.lineModeDTF);
        eraserMode = view.findViewById(R.id.eraserModeDTF);
        strokeWidth = view.findViewById(R.id.strokeWidthDTF);
        canvas = DrawTournamentActivity.canvas;
        r.setOnClickListener(this);
        g.setOnClickListener(this);
        b.setOnClickListener(this);
        y.setOnClickListener(this);
        o.setOnClickListener(this);
        sch.setOnClickListener(this);
        br.setOnClickListener(this);
        p.setOnClickListener(this);
        paintMode.setOnClickListener(this);
        lineMode.setOnClickListener(this);
        eraserMode.setOnClickListener(this);
        strokeWidth.setOnSeekBarChangeListener(this);
        setLayoutValues();
        return view;
    }

    private void setLayoutValues() {
        toolbarHeight = DrawTournamentActivity.toolbarHeight;
        toolbarWidth = DrawTournamentActivity.toolbarWidth;
        int absoluteWidth = toolbarWidth / 2;
        l.getLayoutParams().width = absoluteWidth;
        l.getLayoutParams().height = toolbarHeight;
        sts.getLayoutParams().width = absoluteWidth;
        sts.getLayoutParams().height = absoluteWidth * 2;
        leftSt.getLayoutParams().width = absoluteWidth / 2;
        leftSt.getLayoutParams().height = absoluteWidth * 2;
        rightSt.getLayoutParams().width = absoluteWidth / 2;
        rightSt.getLayoutParams().height = absoluteWidth * 2;
        int colorButSize = absoluteWidth / 2;
        rRes = new ImageRes(R.drawable.red, r, colorButSize, colorButSize);
        gRes = new ImageRes(R.drawable.green, g, colorButSize, colorButSize);
        bRes = new ImageRes(R.drawable.blue, b, colorButSize, colorButSize);
        yRes = new ImageRes(R.drawable.yellow, y, colorButSize, colorButSize);
        oRes = new ImageRes(R.drawable.orange, o, colorButSize, colorButSize);
        schRes = new ImageRes(R.drawable.black, sch, colorButSize, colorButSize);
        brRes = new ImageRes(R.drawable.brown, br, colorButSize, colorButSize);
        pRes = new ImageRes(R.drawable.purplre, p, colorButSize, colorButSize);
        paintModeRes = new ImageRes(R.drawable.brush, paintMode,
                colorButSize, colorButSize);
        lineModeRes = new ImageRes(R.drawable.line, lineMode,
                colorButSize, colorButSize);
        eraserModeRes = new ImageRes(R.drawable.rubber, eraserMode,
                colorButSize, colorButSize);
        strokeWidth.getLayoutParams().height = (int) (toolbarWidth / 2.3);
        strokeWidth.getLayoutParams().width = (int) (toolbarHeight * 0.85);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rDTF: { //Red
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 255, 0, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.gDTF: { //Green
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 255, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.bDTF: { //Blue
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 0, 255);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.yDTF: { //Yellow
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 245, 220, 15);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.oDTF: { //Orange
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 250, 140, 15);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.schDTF: { //Black (schwarz)
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 0, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.brDTF: { //Brown
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 100, 70, 30);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.pDTF: { //Purple
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 155, 30, 245);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.paintModeDTF: {
                canvas.setPaintMode();
                break;
            }
            case R.id.lineModeDTF: {
                canvas.setLineMode();
                break;
            }
            case R.id.eraserModeDTF: {
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
            case R.id.strokeWidthMGF:{
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
}
