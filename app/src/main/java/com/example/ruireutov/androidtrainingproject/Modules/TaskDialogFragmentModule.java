package com.example.ruireutov.androidtrainingproject.Modules;

import com.example.ruireutov.androidtrainingproject.Presenters.ITaskDialogPresenterControl;
import com.example.ruireutov.androidtrainingproject.Presenters.TaskDialogPresenter;
import com.example.ruireutov.androidtrainingproject.Scopes.TaskDialogFragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskDialogFragmentModule {
    @Provides
    @TaskDialogFragmentScope
    ITaskDialogPresenterControl taskDialogPresenterControl(TaskDialogPresenter taskDialogPresenter) {
        return taskDialogPresenter;
    }
}
