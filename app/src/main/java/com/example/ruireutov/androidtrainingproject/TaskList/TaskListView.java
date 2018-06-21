package com.example.ruireutov.androidtrainingproject.TaskList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruireutov.androidtrainingproject.R;

public class TaskListView extends Fragment implements ITaskListViewControl{
    private ITaskListPresenterControl presenterControl;

    public TaskListView() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenterControl = new TaskListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }

    @Override
    public void newTask() {

    }
}
