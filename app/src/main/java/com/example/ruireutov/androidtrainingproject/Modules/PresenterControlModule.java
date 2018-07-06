package com.example.ruireutov.androidtrainingproject.Modules;

import com.example.ruireutov.androidtrainingproject.Presenters.TaskDialogPresenter;
import com.example.ruireutov.androidtrainingproject.Presenters.TaskListPresenter;
import com.example.ruireutov.androidtrainingproject.Repository.TaskRepositoryControl;
import com.example.ruireutov.androidtrainingproject.Scopes.PresenterControlScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterControlModule {
    @Provides
    @PresenterControlScope
    public TaskListPresenter taskListPresenter(TaskRepositoryControl taskRepositoryControl) {
        return new TaskListPresenter(taskRepositoryControl);
    }

    @Provides
    @PresenterControlScope
    public TaskDialogPresenter taskDialogPresenter() {
        return new TaskDialogPresenter();
    }
}
