package com.example.ruireutov.androidtrainingproject.Presenters;

import com.example.ruireutov.androidtrainingproject.Model.Task;

public interface ITaskListPresenterControl extends IPresenter{
    void addTask(String label);
    void updateTask(Task task);
    void deleteTask(Task task);
}
