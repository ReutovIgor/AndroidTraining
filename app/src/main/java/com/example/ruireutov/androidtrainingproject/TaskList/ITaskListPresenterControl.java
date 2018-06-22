package com.example.ruireutov.androidtrainingproject.TaskList;

import com.example.ruireutov.androidtrainingproject.Model.Task;

public interface ITaskListPresenterControl {
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(Task task);
}
