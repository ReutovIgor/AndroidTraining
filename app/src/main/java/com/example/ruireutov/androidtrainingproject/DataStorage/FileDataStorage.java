package com.example.ruireutov.androidtrainingproject.DataStorage;

import android.util.Log;

import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileDataStorage implements IDataStorage{
    private static final String FILE_DATA_STORAGE_LOG_TAG = "FileDataStorage";
    private File taskListStorage;

    public FileDataStorage(String path) {
        File dir = new File(path);
        if(dir.isDirectory()) {
            taskListStorage = new File(dir, "tasks.json");
            if (!taskListStorage.exists()) {
                try {
                    taskListStorage.createNewFile();
                    saveTaskList(new ArrayList<Task>());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            else {
//                saveTaskList(new ArrayList<Task>());
//            }
        }
    }

    @Override
    public List<Task> getTaskList() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(taskListStorage));

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            reader.close();

        } catch (FileNotFoundException e) {
            Log.e(FILE_DATA_STORAGE_LOG_TAG, "saveTaskList: ", e);
        } catch (IOException e) {
            Log.e(FILE_DATA_STORAGE_LOG_TAG, "saveTaskList: ", e);
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(stringBuilder.toString(),new TypeToken<List<Task>>(){}.getType());
    }

    @Override
    public void saveTaskList(List<Task> taskList) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String str = gson.toJson(taskList);

        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(taskListStorage));
            writer.write(str);
            writer.close();
        } catch (FileNotFoundException e) {
            Log.e(FILE_DATA_STORAGE_LOG_TAG, "saveTaskList: ", e);
        } catch (IOException e) {
            Log.e(FILE_DATA_STORAGE_LOG_TAG, "saveTaskList: ", e);
        }


    }
}
