package com.example.ruireutov.androidtrainingproject.Presenters;

import android.content.Context;

import com.example.ruireutov.androidtrainingproject.MainApplication;
import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Repository.TaskRepositoryControl;
import com.example.ruireutov.androidtrainingproject.Views.ITaskListViewControl;
import com.example.ruireutov.androidtrainingproject.Utils.IObserver;
import com.example.ruireutov.androidtrainingproject.Views.IView;

public class TaskListPresenter implements ITaskListPresenterControl {
    private ITaskListViewControl viewControl;
    private TaskRepositoryControl taskRepositoryControl;

    public TaskListPresenter(Context context) {
        taskRepositoryControl = new TaskRepositoryControl(((MainApplication)context.getApplicationContext()).getFileDataStorage());
        taskRepositoryControl.addListener(this::onChanged);
    }

    @Override
    public void addTask(String label) {
        taskRepositoryControl.addTask(label);
    }

    @Override
    public void updateTask(Task task) {
        taskRepositoryControl.updateTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepositoryControl.deleteTask(task);
    }

    @Override
    public void bindView(IView view) {
        this.viewControl = (ITaskListViewControl) view;
        viewControl.setTaskList(taskRepositoryControl.getTasks());
    }

    @Override
    public void unbindView() {
        this.viewControl = null;
    }

    private void onChanged(IObserver some) {
        if(viewControl != null)
            viewControl.setTaskList(taskRepositoryControl.getTasks());
    }
}
