package com.example.ruireutov.androidtrainingproject.Components;

import com.example.ruireutov.androidtrainingproject.Modules.TaskListFragmentModule;
import com.example.ruireutov.androidtrainingproject.Scopes.TaskListFragmentScope;
import com.example.ruireutov.androidtrainingproject.Views.TaskListView;

import dagger.Component;

@TaskListFragmentScope
@Component(modules = TaskListFragmentModule.class, dependencies = PresenterControlComponent.class)
public interface TaskListFragmentComponent {
    void injectTaskListView(TaskListView taskListView);
}
