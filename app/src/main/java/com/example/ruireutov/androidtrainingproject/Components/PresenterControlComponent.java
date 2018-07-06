package com.example.ruireutov.androidtrainingproject.Components;

import com.example.ruireutov.androidtrainingproject.Modules.PresenterControlModule;
import com.example.ruireutov.androidtrainingproject.Presenters.TaskDialogPresenter;
import com.example.ruireutov.androidtrainingproject.Presenters.TaskListPresenter;
import com.example.ruireutov.androidtrainingproject.Repository.TaskRepositoryControl;
import com.example.ruireutov.androidtrainingproject.Scopes.PresenterControlScope;

import dagger.Component;

@PresenterControlScope
@Component(modules = {PresenterControlModule.class}, dependencies = TaskRepositoryControl.class)
public interface PresenterControlComponent {
    TaskListPresenter getTaskListPresenter();
    TaskDialogPresenter getTaskDialogPresenter();
}
