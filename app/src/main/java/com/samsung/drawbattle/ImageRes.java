package com.samsung.drawbattle;

public class ImageRes {
    private int resId;
    private String width;
    private String height;

    public ImageRes(int resId, String width, String height) {
        this.resId = resId;
        this.width = width;
        this.height = height;
    }

    public int getResId() {
        return resId;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
