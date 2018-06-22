package com.example.ruireutov.androidtrainingproject.TaskList;

import android.content.Context;

import com.example.ruireutov.androidtrainingproject.MainApplication;
import com.example.ruireutov.androidtrainingproject.Repository.TaskRepositoryControl;

class TaskListPresenter implements ITaskListPresenterControl{
    private ITaskListViewControl viewControl;
    private TaskRepositoryControl taskRepositoryControl;

    TaskListPresenter(ITaskListViewControl viewControl, Context context) {
        this.viewControl = viewControl;
        taskRepositoryControl = new TaskRepositoryControl(((MainApplication)context.getApplicationContext()).getFileDataStorage());
    }
}
