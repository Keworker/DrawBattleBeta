package com.samsung.drawbattle.fragments;

import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.samsung.drawbattle.R;
import com.samsung.drawbattle.activities.maingame.MainGameActivity;
import com.samsung.drawbattle.classes.ImageRes;
import com.samsung.drawbattle.classes.KeworkerCanvas;

public class ToolbarFragmentMG extends Fragment
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    //Red, green, blue, yellow, orange, black, brown, purple
    protected ImageButton r, g, b, y, o, sch, br, p,
            paintMode, lineMode, eraserMode, stickerAdd;
    protected ImageRes rRes, gRes, bRes, yRes, oRes, schRes, brRes, pRes,
            paintModeRes, lineModeRes, eraserModeRes, stickerAddRes;
    protected SeekBar strokeWidth;
    protected KeworkerCanvas canvas;
    protected int colorButtonSize, modeButtonSize;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_text, container, false);
        r = view.findViewById(R.id.rMGF);
        Log.d("My", Integer.toString(r.getId()));
        g = view.findViewById(R.id.gMGF);
        b = view.findViewById(R.id.bMGF);
        y = view.findViewById(R.id.yMGF);
        o = view.findViewById(R.id.oMGF);
        sch = view.findViewById(R.id.schMGF);
        br = view.findViewById(R.id.brMGF);
        p = view.findViewById(R.id.pMGF);
        paintMode = view.findViewById(R.id.paintModeMGF);
        lineMode = view.findViewById(R.id.lineModeMGF);
        eraserMode = view.findViewById(R.id.eraserModeMGF);
        stickerAdd = view.findViewById(R.id.stickerAddMGF);
        strokeWidth = view.findViewById(R.id.strokeWidthMGF);
        this.canvas = MainGameActivity.canvas;
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
        stickerAdd.setOnClickListener(this);
        strokeWidth.setOnSeekBarChangeListener(this);
        return inflater.inflate(R.layout.fragment_edit_text, container, false);
    }

    public void setLayoutValues(int colorButtonSize, int modeButtonSize) {
        rRes = new ImageRes(R.drawable.red, r, colorButtonSize, colorButtonSize);
        gRes = new ImageRes(R.drawable.green, g, colorButtonSize, colorButtonSize);
        bRes = new ImageRes(R.drawable.blue, b, colorButtonSize, colorButtonSize);
        yRes = new ImageRes(R.drawable.yellow, y, colorButtonSize, colorButtonSize);
        oRes = new ImageRes(R.drawable.orange, o, colorButtonSize, colorButtonSize);
        schRes = new ImageRes(R.drawable.black, sch, colorButtonSize, colorButtonSize);
        brRes = new ImageRes(R.drawable.brown, br, colorButtonSize, colorButtonSize);
        pRes = new ImageRes(R.drawable.purplre, p, colorButtonSize, colorButtonSize);
        paintModeRes = new ImageRes(R.drawable.brush, paintMode,
                modeButtonSize, modeButtonSize);
        lineModeRes = new ImageRes(R.drawable.line, lineMode,
                modeButtonSize, modeButtonSize);
        eraserModeRes = new ImageRes(R.drawable.rubber, eraserMode,
                modeButtonSize, modeButtonSize);
        stickerAddRes = new ImageRes(R.drawable.stiker, stickerAdd,
                modeButtonSize, modeButtonSize);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rMGF: { //Red
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 255, 0, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.gMGF: { //Green
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 255, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.bMGF: { //Blue
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 0, 255);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.yMGF: { //Yellow
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 245, 220, 15);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.oMGF: { //Orange
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 250, 140, 15);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.schMGF: { //Black (schwarz)
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 0, 0, 0);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.brMGF: { //Brown
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 100, 70, 30);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.pMGF: { //Purple
                while (true) {
                    try {
                        canvas.setPaintARGB(255, 155, 30, 245);
                        break;
                    }
                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
                }
                break;
            }
            case R.id.paintModeMGF: {
                canvas.setPaintMode();
                break;
            }
            case R.id.lineModeMGF: {
                canvas.setLineMode();
                break;
            }
            case R.id.eraserModeMGF: {
                canvas.setEraserMode();
                break;
            }
            case R.id.stickerAddMGF: {

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