package com.samsung.drawbattle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import android.app.Fragment;
import com.samsung.drawbattle.R;
import com.samsung.drawbattle.classes.KeworkerCanvas;

public class ToolbarFragment extends Fragment
        /*implements View.OnClickListener, SeekBar.OnSeekBarChangeListener*/ {
    //Red, green, blue, yellow, orange, black, brown, purple
    public ImageButton r, g, b, y, o, sch, br, p, paintMode, lineMode, eraserMode, stickerAdd;
    public SeekBar strokeWidth;
    public KeworkerCanvas canvas;

    public static ToolbarFragment newInstance(KeworkerCanvas canvas) {
        ToolbarFragment toolbarFragment = new ToolbarFragment();
        toolbarFragment.canvas = canvas;
        return toolbarFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toolbar,
                container, false);
        r = view.findViewById(R.id.rFT);
        g = view.findViewById(R.id.gFT);
        b = view.findViewById(R.id.bFT);
        y = view.findViewById(R.id.yFT);
        o = view.findViewById(R.id.oFT);
        sch = view.findViewById(R.id.schFT);
        br = view.findViewById(R.id.brFT);
        p = view.findViewById(R.id.pFT);
        paintMode = view.findViewById(R.id.paintModeFT);
        lineMode = view.findViewById(R.id.lineModeFT);
        eraserMode = view.findViewById(R.id.eraserModeFT);
        stickerAdd = view.findViewById(R.id.stickerAddFT);
        strokeWidth = view.findViewById(R.id.strokeWidthFT);
//        r.setOnClickListener(this);
//        g.setOnClickListener(this);
//        b.setOnClickListener(this);
//        y.setOnClickListener(this);
//        o.setOnClickListener(this);
//        sch.setOnClickListener(this);
//        br.setOnClickListener(this);
//        p.setOnClickListener(this);
//        paintMode.setOnClickListener(this);
//        lineMode.setOnClickListener(this);
//        eraserMode.setOnClickListener(this);
//        stickerAdd.setOnClickListener(this);
//        strokeWidth.setOnSeekBarChangeListener(this);
        return view;
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.r: { //Red
//                while (true) {
//                    try {
//                        canvas.setPaintARGB(255, 255, 0, 0);
//                        break;
//                    }
//                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
//                }
//                break;
//            }
//            case R.id.g: { //Green
//                while (true) {
//                    try {
//                        canvas.setPaintARGB(255, 0, 255, 0);
//                        break;
//                    }
//                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
//                }
//                break;
//            }
//            case R.id.b: { //Blue
//                while (true) {
//                    try {
//                        canvas.setPaintARGB(255, 0, 0, 255);
//                        break;
//                    }
//                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
//                }
//                break;
//            }
//            case R.id.y: { //Yellow
//                while (true) {
//                    try {
//                        canvas.setPaintARGB(255, 245, 220, 15);
//                        break;
//                    }
//                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
//                }
//                break;
//            }
//            case R.id.o: { //Orange
//                while (true) {
//                    try {
//                        canvas.setPaintARGB(255, 250, 140, 15);
//                        break;
//                    }
//                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
//                }
//                break;
//            }
//            case R.id.sch: { //Black (schwarz)
//                while (true) {
//                    try {
//                        canvas.setPaintARGB(255, 0, 0, 0);
//                        break;
//                    }
//                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
//                }
//                break;
//            }
//            case R.id.br: { //Brown
//                while (true) {
//                    try {
//                        canvas.setPaintARGB(255, 100, 70, 30);
//                        break;
//                    }
//                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
//                }
//                break;
//            }
//            case R.id.p: { //Purple
//                while (true) {
//                    try {
//                        canvas.setPaintARGB(255, 155, 30, 245);
//                        break;
//                    }
//                    catch (KeworkerCanvas.KeworkerException keworkerException) {}
//                }
//                break;
//            }
//            case R.id.paintMode: {
//                canvas.setPaintMode();
//                break;
//            }
//            case R.id.lineMode: {
//                canvas.setLineMode();
//                break;
//            }
//            case R.id.eraserMode: {
//                canvas.setEraserMode();
//                break;
//            }
//            case R.id.stickerAddFT: {
//
//                break;
//            }
//        }
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {}
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {}
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        switch (seekBar.getId()) {
//            case R.id.strokeWidth: {
//                while (true) {
//                    try {
//                        canvas.setWidth((float) seekBar.getProgress());
//                        break;
//                    }
//                    catch (KeworkerCanvas.KeworkerException keworkerException) {
//                        break;
//                    }
//                }
//                break;
//            }
//        }
//    }
}