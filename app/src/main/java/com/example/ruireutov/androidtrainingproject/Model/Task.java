package com.example.ruireutov.androidtrainingproject.Model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

public class Task extends BaseObservable implements Parcelable{
    private final int id;
    private String label;
    private boolean status;

    public Task(int id, String label) {
        this.id = id;
        this.label = label;
        status = false;
    }

    public int getId() {
        return id;
    }

    @Bindable
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isDone () {
        return status;
    }
    public void setStatus(boolean isDone) {
        status = isDone;
    }

    //  Parcelable part
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.label);
        dest.writeInt(this.status ? 1 : 0);
    }


    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    private Task(Parcel in) {
        this.id = in.readInt();
        this.label = in.readString();
        int status = in.readInt();
        this.status = status != 0;
    }
}
