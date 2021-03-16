package com.example.baseadapteradd;


import android.graphics.drawable.Drawable;

public class ItemData {


    private Drawable image;
    private String title;
    private String subtitle;


    public ItemData(Drawable image, String title, String subtitle) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;

    }

    public Drawable getImage() {
        return image;
    }


    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }


}
