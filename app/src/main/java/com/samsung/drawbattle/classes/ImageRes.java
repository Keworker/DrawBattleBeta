package com.samsung.drawbattle.classes;

import android.util.Log;
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
//        imageView.setMinimumWidth(this.width - 1);
//        imageView.setMaxWidth(this.width);
//        imageView.setMinimumHeight(this.height - 1);
//        imageView.setMaxHeight(this.height);
//        imageView.setBackgroundResource(resId);
        Log.d("My", Integer.toString(imageView.getHeight()) + " высота, толщина - " +
                Integer.toString(imageView.getWidth()));
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
