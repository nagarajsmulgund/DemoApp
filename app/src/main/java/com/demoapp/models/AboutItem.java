package com.demoapp.models;

import com.google.gson.annotations.SerializedName;

public class AboutItem {
    private String title;
    @SerializedName("rows")
    private DataItem[] DataItem;

    public String getTitle() {
        return title;
    }

    public DataItem[] getDataItem() {
        return DataItem;
    }


}
