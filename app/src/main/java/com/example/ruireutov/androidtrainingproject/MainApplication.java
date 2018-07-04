package com.example.ruireutov.androidtrainingproject;

import android.app.Application;

//import com.example.ruireutov.androidtrainingproject.Components.DaggerTaskControlComponent;
//import com.example.ruireutov.androidtrainingproject.Components.PresenterControlComponent;
//import com.example.ruireutov.androidtrainingproject.Components.TaskControlComponent;
//import com.example.ruireutov.androidtrainingproject.Modules.TaskRepositoryModule;

public class MainApplication extends Application {
//    private TaskControlComponent taskControlComponent;
//    private PresenterControlComponent presenterControlComponent;


    @Override
    public void onCreate() {
        super.onCreate();
//        taskControlComponent = DaggerTaskControlComponent.builder()
//                .taskRepositoryModule(new TaskRepositoryModule(getApplicationInfo().dataDir))
//                .build();
    }

//    public TaskControlComponent getTaskControlComponent() {
//        return taskControlComponent;
//    }
}
