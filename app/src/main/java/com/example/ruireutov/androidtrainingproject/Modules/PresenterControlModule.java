package com.example.ruireutov.androidtrainingproject.Modules;

import com.example.ruireutov.androidtrainingproject.Presenters.IPresenter;
import com.example.ruireutov.androidtrainingproject.Presenters.TaskListPresenter;
import com.example.ruireutov.androidtrainingproject.Repository.TaskRepositoryControl;
import com.example.ruireutov.androidtrainingproject.Scopes.TaskControlScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterControlModule {
    @Provides
    @TaskControlScope
    public IPresenter taskListPresenter(TaskRepositoryControl taskRepositoryControl) {
        return new TaskListPresenter(taskRepositoryControl);
    }
}
