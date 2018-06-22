package com.example.ruireutov.androidtrainingproject.Repository;

import com.example.ruireutov.androidtrainingproject.DataStorage.IDataStorage;
import com.example.ruireutov.androidtrainingproject.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryControl{
    private IDataStorage storage;
    private List<Task> taskList;

    public TaskRepositoryControl(IDataStorage storage) {
        this.storage = storage;
        taskList = new ArrayList<>();
        taskList = getTasks();
    }

    public void setDataStorage(IDataStorage storage) {
        this.storage = storage;
    }

    public List<Task> getTasks() {
        return storage.getTaskList();
    }

    public void addTask(Task task) {
        taskList.add(task);
        saveTaskList();
    }

    public void updateTask(Task task) {
        taskList.add(task.getId(), task);
        saveTaskList();
    }

    public void deleteTask(Task task) {
        taskList.remove(task.getId());
        saveTaskList();
    }

    private void saveTaskList() {
        storage.saveTaskList(taskList);
    }
}
