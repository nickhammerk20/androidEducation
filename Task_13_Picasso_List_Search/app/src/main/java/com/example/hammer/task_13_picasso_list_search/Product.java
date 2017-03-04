package com.example.hammer.task_13_picasso_list_search;

import java.io.Serializable;

/**
 * Created by hammer on 04.03.2017.
 */

public class Product implements Serializable {

    private int mId;
    private String mTitle;
    private String mPerma_Link;

    public Product (int mId, String mTitle, String mPerma_Link){
        this.mId = mId;
        this.mTitle = mTitle;
        this.mPerma_Link = mPerma_Link;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPerma_Link() {
        return mPerma_Link;
    }

    public void setmPerma_Link(String mPerma_Link) {
        this.mPerma_Link = mPerma_Link;
    }

    @Override
    public String toString() {
        return "Product{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mPerma_Link='" + mPerma_Link + '\'' +
                '}';}
}
