package com.example.ruireutov.androidtrainingproject.Components;

import com.example.ruireutov.androidtrainingproject.Modules.TaskRepositoryModule;
import com.example.ruireutov.androidtrainingproject.Repository.TaskRepositoryControl;
import com.example.ruireutov.androidtrainingproject.Scopes.TaskControlScope;

import dagger.Component;

@TaskControlScope
@Component(modules = TaskRepositoryModule.class)
public interface TaskControlComponent {
    TaskRepositoryControl getTaskRepositoryControl();
}
