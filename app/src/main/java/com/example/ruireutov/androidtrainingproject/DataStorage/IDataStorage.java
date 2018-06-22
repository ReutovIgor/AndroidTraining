package com.example.ruireutov.androidtrainingproject.DataStorage;

import com.example.ruireutov.androidtrainingproject.Model.Task;

import java.util.List;

public interface IDataStorage {
    List<Task> getTaskList();
    void saveTaskList(List<Task> taskList);
}
