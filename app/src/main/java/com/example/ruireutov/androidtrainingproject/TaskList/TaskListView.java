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
import com.example.ruireutov.androidtrainingproject.Views.TaskDialogView;

import java.util.List;

public class TaskListView extends Fragment implements ITaskListViewControl{
    public static final int TASK_LIST_ID = 0;

    private TaskListAdapter taskListAdapter;
    private ITaskListPresenterControl presenterControl;
    TaskDialogView taskDialogView;

    public TaskListView() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

            @Override
            public void onTaskUpdate(Task task) {
                Bundle args = new Bundle();
                args.putInt("id", task.getId());
                args.putString("description", task.getLabel());
                taskDialogView.setArguments(args);

                setTargetFragmentForDialog();
                taskDialogView.showUpdateTaskDialog(getFragmentManager());
            }

            @Override
            public void onTaskDelete(Task task) {
                Bundle args = new Bundle();
                args.putInt("id", task.getId());
                args.putString("description", task.getLabel());
                taskDialogView.setArguments(args);

                setTargetFragmentForDialog();
                taskDialogView.showDeleteTaskDialog(getFragmentManager());
            }
        });
        list.setAdapter(taskListAdapter);

        FloatingActionButton btn = v.findViewById(R.id.button_new_task);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTargetFragmentForDialog();
                taskDialogView.showNewTaskDialog(getFragmentManager());
            }
        });

        presenterControl = new TaskListPresenter(this, getActivity());
        taskDialogView = new TaskDialogView();

        return v;
    }

    @Override
    public void setTaskList(List<Task> taskList) {
        taskListAdapter.setTaskList(taskList);
    }

    public void onNewTask(String description) {
        presenterControl.addTask(taskListAdapter.addTask(description));
    }

    public void onUpdateTask(int id, String label) {
        Task t = taskListAdapter.updateTask(id, label);
        if(t != null)
            presenterControl.updateTask(t);
    }

    public void onDeleteTask(int id) {
        Task t = taskListAdapter.deleteTask(id);
        if(t != null)
            presenterControl.deleteTask(t);
    }

    private void setTargetFragmentForDialog() {
        taskDialogView.setTargetFragment(this, TASK_LIST_ID);
    }
}
