package com.example.ruireutov.androidtrainingproject.Modules;

import com.example.ruireutov.androidtrainingproject.DataStorage.FileDataStorage;
import com.example.ruireutov.androidtrainingproject.DataStorage.IDataStorage;
import com.example.ruireutov.androidtrainingproject.Repository.TaskRepositoryControl;
import com.example.ruireutov.androidtrainingproject.Scopes.TaskControlScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskRepositoryModule {
    private final String filePath;

    public TaskRepositoryModule(String filePath) {
        this.filePath = filePath;
    }

    @Provides
    @TaskControlScope
    public TaskRepositoryControl providesTaskRepositoryControl(IDataStorage dataStorage) {
        return new TaskRepositoryControl(dataStorage);
    }

    @Provides
    @TaskControlScope
    public IDataStorage providesDataStorage(Gson gson) {
        return new FileDataStorage(filePath, gson);
    }

    @Provides
    @TaskControlScope
    public Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
}
