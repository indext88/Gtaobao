package com.example.indext.gtaobao;

import android.widget.ImageView;

public class Intertaobao {
    private String name1;
    private String name2;
    private String name3;
    private int imageViewId;

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public int getImageViewId() {
        return imageViewId;
    }

    public void setImageViewId(int imageViewId) {
        this.imageViewId = imageViewId;
    }

    public Intertaobao(String name1, String name2, String name3, int imageViewId) {
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.imageViewId = imageViewId;
    }
}