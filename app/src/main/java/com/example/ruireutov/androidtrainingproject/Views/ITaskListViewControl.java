package com.example.ruireutov.androidtrainingproject.Views;

import android.support.v4.app.DialogFragment;
import com.example.ruireutov.androidtrainingproject.Model.Task;

import java.util.List;

public interface ITaskListViewControl {
    void setTaskList(List<Task> taskList);
    void showDialogFragment(DialogFragment dialogFragment);
}
