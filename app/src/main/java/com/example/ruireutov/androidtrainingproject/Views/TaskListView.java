package com.example.ruireutov.androidtrainingproject.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ruireutov.androidtrainingproject.MainApplication;
import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Presenters.ITaskListPresenterControl;
import com.example.ruireutov.androidtrainingproject.R;
import com.example.ruireutov.androidtrainingproject.Adapters.TaskListAdapter;

import java.util.List;

public class TaskListView extends Fragment implements ITaskListViewControl, IView {
    public static final int TASK_LIST_ID = 0;

    private TaskListAdapter taskListAdapter;
    private ITaskListPresenterControl presenterControl;

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
                args.putParcelable("task", task);
                TaskDialogView dialogView = getTaskDialogView();
                dialogView.setArguments(args);
            }

            @Override
            public void onTaskDelete(Task task) {
//                Bundle args = new Bundle();
//                args.putInt("id", task.getId());
//                args.putString("description", task.getLabel());
//                taskDialogView.setArguments(args);
//
//                setTargetFragmentForDialog();
//                taskDialogView.showDeleteTaskDialog(getFragmentManager());
            }
        });
        list.setAdapter(taskListAdapter);

        FloatingActionButton btn = v.findViewById(R.id.button_new_task);
        btn.setOnClickListener((View view) -> {
            TaskDialogView dialogView = getTaskDialogView();
            dialogView.showNewTaskDialog(getFragmentManager());
        });


        presenterControl = (ITaskListPresenterControl) ((MainApplication)getActivity().getApplicationContext()).getTaskListPresenter();
        presenterControl.bindView(this);

        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        presenterControl.unbindView();
    }

    @Override
    public void setTaskList(List<Task> taskList) {
        taskListAdapter.setTaskList(taskList);
    }

    public void onNewTask(String label) {
        presenterControl.addTask(label);
    }

    public void onUpdateTask(Task t) {
        presenterControl.updateTask(t);
    }

    public void onDeleteTask(Task t) {
        presenterControl.deleteTask(t);
    }

    private TaskDialogView getTaskDialogView() {
        TaskDialogView dialogView = new TaskDialogView();
        dialogView.setTargetFragment(this, TASK_LIST_ID);
        return dialogView;
    }
}
