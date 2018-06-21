package com.example.ruireutov.androidtrainingproject.DataStorage;

import com.example.ruireutov.androidtrainingproject.Model.Task;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileDataStorage {
    private File taskListStorage;

    public FileDataStorage(String path) {
        File dir = new File(path);
        if(dir.isDirectory()) {
            taskListStorage = new File(dir, "tasks.json");
            if (!taskListStorage.exists()) {
                try {
                    taskListStorage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Task> readTaskListFromFile() {
        return null;
    }

    public void writeTaskListToFile(List<Task> taskList) {

    }
}
