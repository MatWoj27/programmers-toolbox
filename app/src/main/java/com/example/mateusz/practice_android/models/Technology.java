package com.example.mateusz.practice_android.models;

/**
 * Created by Mateusz on 7/7/2018.
 */

public class Technology {
    private int iconId;
    private String name;
    private String description;

    public Technology(int iconId, String name, String description) {
        this.iconId = iconId;
        this.name = name;
        this.description = description;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
