package com.example.mateusz.practice_android.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by Mateusz on 7/7/2018.
 */

public class Technology implements Parcelable {

    private int iconId;
    private String name;
    private String description;

    public Technology(int iconId, String name, String description) {
        this.iconId = iconId;
        this.name = name;
        this.description = description;
    }

    private Technology(@NonNull Parcel in) {
        iconId = in.readInt();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<Technology> CREATOR = new Creator<Technology>() {
        @Override
        public Technology createFromParcel(Parcel in) {
            return new Technology(in);
        }

        @Override
        public Technology[] newArray(int size) {
            return new Technology[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(iconId);
        parcel.writeString(name);
        parcel.writeString(description);
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
