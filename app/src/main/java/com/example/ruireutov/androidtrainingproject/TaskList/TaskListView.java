package com.example.ruireutov.androidtrainingproject.TaskList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.R;

import java.util.List;

public class TaskListView extends Fragment implements ITaskListViewControl{
    private TaskListAdapter taskListAdapter;
    private ITaskListPresenterControl presenterControl;

    public TaskListView() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterControl = new TaskListPresenter(this, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_list, container, false);

        ListView list = v.findViewById(R.id.list_tasks);
        taskListAdapter = new TaskListAdapter(new TaskListAdapter.TaskAdapterCallback() {
            @Override
            public void onTaskChanged(Task task) {
                presenterControl.updateTask(task);
            }
        });
        list.setAdapter(taskListAdapter);

        FloatingActionButton btn = v.findViewById(R.id.button_new_task);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open Dialog view
            }
        });

        return v;
    }

    @Override
    public void setTaskList(List<Task> taskList) {
        taskListAdapter.setTaskList(taskList);
    }
}
