package com.example.ruireutov.androidtrainingproject.Presenters;

import com.example.ruireutov.androidtrainingproject.Adapters.TaskListAdapter;

public interface ITaskListPresenterControl extends IPresenter{
    TaskListAdapter.TaskAdapterCallback getAdapterItemHandlers();
}
