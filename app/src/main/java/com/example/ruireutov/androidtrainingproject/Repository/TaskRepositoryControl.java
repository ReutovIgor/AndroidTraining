package com.example.ruireutov.androidtrainingproject.Repository;

import com.example.ruireutov.androidtrainingproject.DataStorage.IDataStorage;
import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Utils.AObsrver;
import com.example.ruireutov.androidtrainingproject.Utils.IObserver;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryControl extends AObsrver implements IObserver{
    private IDataStorage localStorage;
    private final List<Task> taskList;

    public TaskRepositoryControl(IDataStorage storage) {
        this.localStorage = storage;
        taskList = new ArrayList<>();
    }

    public List<Task> getTasks() {
        List<Task> list = localStorage.getTaskList();

        if(list != null) {
            taskList.clear();
            taskList.addAll(list);
        }
        return taskList;
    }

    public void addTask(String label) {
        int id = 0;
        if (taskList.size() > 0) {
            id = taskList.get(taskList.size() - 1).getId() + 1;
        }
        Task task = new Task(id, label);
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
        localStorage.saveTaskList(taskList);
        onChanged();
    }
}
