package com.example.ruireutov.androidtrainingproject.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable{
    private final int id;
    private String label;
    private boolean status;

    public Task(int id, String label) {
        this.id = id;
        this.label = label;
        status = false;
    }

    public int getId() { return id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public boolean isDone () { return status; }
    public void setStatus(boolean isDone) { status = isDone; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
