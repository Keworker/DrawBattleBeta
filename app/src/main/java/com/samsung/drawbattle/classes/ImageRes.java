package com.samsung.drawbattle.classes;

import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;

public class ImageRes {
    private int resId;
    private int width;
    private int height;
    private ImageView imageView;

    public ImageRes(int resId, ImageView imageView, float width, float height) {
        this.resId = resId;
        this.width = (int) Math.floor(width);
        this.height = (int) Math.floor(height);
        this.imageView = imageView;
        imageView.getLayoutParams().width = (int) width;
        imageView.getLayoutParams().height = (int) height;
        imageView.setBackgroundResource(resId);
    }

    public int getResId() {
        return resId;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
