package com.example.ruireutov.androidtrainingproject;

import android.app.Application;

import com.example.ruireutov.androidtrainingproject.DataStorage.FileDataStorage;
import com.example.ruireutov.androidtrainingproject.DataStorage.IDataStorage;

public class MainApplication extends Application {
    IDataStorage fileDataStorage;
    IDataStorage httpDataStorage;
    @Override
    public void onCreate() {
        super.onCreate();
        fileDataStorage = new FileDataStorage(getApplicationInfo().dataDir);
    }

    public IDataStorage getFileDataStorage() {
        return fileDataStorage;
    }

    public IDataStorage getHttpDataStorage() {
        return httpDataStorage;
    }
}
