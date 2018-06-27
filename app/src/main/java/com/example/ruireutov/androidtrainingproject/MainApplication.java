package com.example.ruireutov.androidtrainingproject;

import android.app.Application;

import com.example.ruireutov.androidtrainingproject.DataStorage.FileDataStorage;
import com.example.ruireutov.androidtrainingproject.DataStorage.IDataStorage;
import com.example.ruireutov.androidtrainingproject.DataStorage.ServerDataStorage;

public class MainApplication extends Application {
    IDataStorage fileDataStorage;
    IDataStorage httpDataStorage;
    @Override
    public void onCreate() {
        super.onCreate();
        fileDataStorage = new FileDataStorage(getApplicationInfo().dataDir);
        httpDataStorage = new ServerDataStorage("http",
                this.getString(R.string.server_host),
                this.getString(R.string.server_port),
                this.getString(R.string.server_key));
    }

    public IDataStorage getFileDataStorage() {
        return fileDataStorage;
    }

    public IDataStorage getHttpDataStorage() {
        return httpDataStorage;
    }
}
