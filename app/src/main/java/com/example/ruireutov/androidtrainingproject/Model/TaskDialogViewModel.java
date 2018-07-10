package com.example.ruireutov.androidtrainingproject.Model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class TaskDialogViewModel extends BaseObservable {
    private Task taskData;
    private int dialogType;

    public TaskDialogViewModel(int type, Task task) {
        taskData = task != null ? task : new Task(-1, "");
        dialogType = type;
    }

    @Bindable
    public Task getTaskData() {
        return taskData;
    }

    @Bindable
    public int getDialogType() {
        return dialogType;
    }
}
