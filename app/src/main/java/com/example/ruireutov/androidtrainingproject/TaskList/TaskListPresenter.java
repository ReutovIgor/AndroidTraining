package com.example.ruireutov.androidtrainingproject.TaskList;

import android.content.Context;

import com.example.ruireutov.androidtrainingproject.MainApplication;
import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Repository.TaskRepositoryControl;

class TaskListPresenter implements ITaskListPresenterControl{
    private ITaskListViewControl viewControl;
    private TaskRepositoryControl taskRepositoryControl;

    TaskListPresenter(ITaskListViewControl viewControl, Context context) {
        this.viewControl = viewControl;
        taskRepositoryControl = new TaskRepositoryControl(((MainApplication)context.getApplicationContext()).getFileDataStorage());
        viewControl.setTaskList(taskRepositoryControl.getTasks());
    }

    @Override
    public void addTask(Task task) {
        taskRepositoryControl.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        taskRepositoryControl.updateTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepositoryControl.deleteTask(task);
    }
}
