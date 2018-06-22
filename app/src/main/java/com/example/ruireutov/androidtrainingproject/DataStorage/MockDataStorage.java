package com.example.ruireutov.androidtrainingproject.DataStorage;

import com.example.ruireutov.androidtrainingproject.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class MockDataStorage implements IDataStorage{
    List<Task> tasks;
    public MockDataStorage() {
        tasks = new ArrayList<>();
        tasks.add(new Task(0, "Task1"));
        tasks.add(new Task(1, "Task2"));
        tasks.add(new Task(2, "Task3"));
        tasks.add(new Task(3, "Task4"));
        tasks.add(new Task(4, "Task5"));
    }
    @Override
    public List<Task> getTaskList() {
        return tasks;
    }

    @Override
    public void saveTaskList(List<Task> taskList) {
        tasks = taskList;
    }
}
