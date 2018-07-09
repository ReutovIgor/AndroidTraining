package com.example.ruireutov.androidtrainingproject.Components;

import com.example.ruireutov.androidtrainingproject.Modules.TaskDialogFragmentModule;
import com.example.ruireutov.androidtrainingproject.Scopes.TaskDialogFragmentScope;
import com.example.ruireutov.androidtrainingproject.Views.TaskDialogView;

import dagger.Component;

@TaskDialogFragmentScope
@Component(modules = TaskDialogFragmentModule.class, dependencies = PresenterControlComponent.class)
public interface TaskDialogFragmentComponent {
    void injectTaskDialogView(TaskDialogView taskDialogView);
}
