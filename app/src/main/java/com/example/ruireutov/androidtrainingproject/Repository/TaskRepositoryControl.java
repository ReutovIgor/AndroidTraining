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

    public void addTask(String description) {
        Task newTask = new Task(taskList.size(), description);
        taskList.add(newTask);
        saveTaskList();
    }

    public void updateTaskStatus(int id, boolean status) {
        Task task = taskList.get(id);
        task.setStatus(status);
        taskList.add(id, task);
        saveTaskList();
    }

    public void deleteTask(int id) {
        taskList.remove(id);
        saveTaskList();
    }

    private void saveTaskList() {
        storage.saveTaskList(taskList);
    }
}
