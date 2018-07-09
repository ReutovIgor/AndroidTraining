package com.example.ruireutov.androidtrainingproject.Modules;

import com.example.ruireutov.androidtrainingproject.Adapters.TaskListAdapter;
import com.example.ruireutov.androidtrainingproject.Presenters.ITaskListPresenterControl;
import com.example.ruireutov.androidtrainingproject.Presenters.TaskListPresenter;
import com.example.ruireutov.androidtrainingproject.Scopes.TaskListFragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskListFragmentModule {
    @Provides
    @TaskListFragmentScope
    ITaskListPresenterControl taskListPresenterControl(TaskListPresenter taskListPresenter) {
        return taskListPresenter;
    }

    @Provides
    @TaskListFragmentScope
    TaskListAdapter.TaskAdapterCallback taskAdapterCallback(TaskListPresenter taskListPresenter) {
        return taskListPresenter.getAdapterItemHandlers();
    }
}
