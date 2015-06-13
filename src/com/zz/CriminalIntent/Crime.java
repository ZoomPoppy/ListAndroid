package com.zz.CriminalIntent;

import java.util.*;



/**
 * Created by zz on 2015/6/10.
 */
public class Crime {
    private UUID id;
    private String title;
    private Date mDate;
    private boolean mSolved;
    public Crime(){
        id = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    @Override
    public String toString() {
        return title;
    }
}
