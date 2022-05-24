package com.samsung.drawbattle.classes;

import android.widget.ImageView;
import android.widget.TextView;

public class PublicationMG {
    private int av1, av2;
    private String name1, name2;
    private String image2;
    private String text1;
    private ImageView i;
    private TextView t;

    public PublicationMG(int av1, int av2,
                         String name1, String name2,
                         String text1, String image2) {
        this.av1 = av1;
        this.av2 = av2;
        this.name1 = name1;
        this.name2 = name2;
        this.image2 = image2;
        this.text1 = text1;
    }

    public TextView getT() {
        return t;
    }

    public void setT(TextView t) {
        this.t = t;
    }

    public ImageView getI() {
        return i;
    }

    public void setI(ImageView i) {
        this.i = i;
    }

    public int getAv1() {
        return av1;
    }

    public int getAv2() {
        return av2;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getImage2() {
        return image2;
    }

    public String getText1() {
        return text1;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }
}
