package com.example.ruireutov.androidtrainingproject;

import android.app.Application;

import com.example.ruireutov.androidtrainingproject.DataStorage.FileDataStorage;
import com.example.ruireutov.androidtrainingproject.DataStorage.IDataStorage;
import com.example.ruireutov.androidtrainingproject.DataStorage.ServerDataStorage;
import com.example.ruireutov.androidtrainingproject.Presenters.IPresenter;
import com.example.ruireutov.androidtrainingproject.Presenters.TaskListPresenter;

public class MainApplication extends Application {
    IDataStorage fileDataStorage;
    IDataStorage httpDataStorage;
    IPresenter taskListPresenter;


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
        if(fileDataStorage == null) {
            fileDataStorage = new FileDataStorage(getApplicationInfo().dataDir);
        }
        return fileDataStorage;
    }

    public IDataStorage getHttpDataStorage() {
        if(httpDataStorage == null) {
            httpDataStorage = new ServerDataStorage("http",
                    this.getString(R.string.server_host),
                    this.getString(R.string.server_port),
                    this.getString(R.string.server_key)
            );
        }
        return httpDataStorage;
    }

    public IPresenter getTaskListPresenter() {
        if(taskListPresenter == null) {
            taskListPresenter = new TaskListPresenter(this);
        }

        return taskListPresenter;
    }
}
