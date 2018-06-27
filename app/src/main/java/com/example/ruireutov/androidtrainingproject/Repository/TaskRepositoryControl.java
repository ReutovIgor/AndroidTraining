package com.example.ruireutov.androidtrainingproject.Repository;

import com.example.ruireutov.androidtrainingproject.DataStorage.IDataStorage;
import com.example.ruireutov.androidtrainingproject.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryControl{
    private IDataStorage localStorage;
    private IDataStorage serverStorage;
    private final List<Task> taskList;

    public TaskRepositoryControl(IDataStorage storage) {
        this.localStorage = storage;
        taskList = new ArrayList<>();
    }

    public void setExternalDataStorage(IDataStorage storage) {
        serverStorage = storage;
    }

    public List<Task> getTasks() {
        List<Task> list;
        if(serverStorage != null) {
            list = serverStorage.getTaskList();
        } else {
            list = localStorage.getTaskList();
        }

        if(list != null) {
            taskList.clear();
            taskList.addAll(list);
        }
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
        saveTaskList();
    }

    public void updateTask(Task task) {
        int pos = taskList.indexOf(task);
        if(pos != -1) {
            taskList.set(pos, task);
            saveTaskList();
        }
    }

    public void deleteTask(Task task) {
        int pos = taskList.indexOf(task);
        if(pos != -1) {
            taskList.remove(pos);
            saveTaskList();
        }
    }

    private void saveTaskList() {
        if(serverStorage != null) {
            serverStorage.saveTaskList(taskList);
        }
        localStorage.saveTaskList(taskList);
    }
}
