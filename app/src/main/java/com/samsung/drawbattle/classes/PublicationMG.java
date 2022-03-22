package com.samsung.drawbattle.classes;

public class PublicationMG {
    private int av1, av2, av3, av4, av5, av6;
    private String name1, name2, name3, name4, name5, name6;
    private int image2, image4, image6;
    private String text1, text3, text5;

    public PublicationMG(int av1, int av2, int av3, int av4, int av5, int av6,
                         String name1, String name2, String name3,
                         String name4, String name5, String name6,
                         String text1, int image2, String text3,
                         int image4, String text5, int image6) {
        this.av1 = av1;
        this.av2 = av2;
        this.av3 = av3;
        this.av4 = av4;
        this.av5 = av5;
        this.av6 = av6;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.name5 = name5;
        this.name6 = name6;
        this.image2 = image2;
        this.image4 = image4;
        this.image6 = image6;
        this.text1 = text1;
        this.text3 = text3;
        this.text5 = text5;
    }

    public int getAv1() {
        return av1;
    }

    public int getAv2() {
        return av2;
    }

    public int getAv3() {
        return av3;
    }

    public int getAv4() {
        return av4;
    }

    public int getAv5() {
        return av5;
    }

    public int getAv6() {
        return av6;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getName3() {
        return name3;
    }

    public String getName4() {
        return name4;
    }

    public String getName5() {
        return name5;
    }

    public String getName6() {
        return name6;
    }

    public int getImage2() {
        return image2;
    }

    public int getImage4() {
        return image4;
    }

    public int getImage6() {
        return image6;
    }

    public String getText1() {
        return text1;
    }

    public String getText3() {
        return text3;
    }

    public String getText5() {
        return text5;
    }
}
