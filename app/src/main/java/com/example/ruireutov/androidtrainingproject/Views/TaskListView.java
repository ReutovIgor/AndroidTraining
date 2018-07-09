package com.example.ruireutov.androidtrainingproject.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ruireutov.androidtrainingproject.Components.DaggerTaskListFragmentComponent;
import com.example.ruireutov.androidtrainingproject.Components.TaskListFragmentComponent;
import com.example.ruireutov.androidtrainingproject.MainApplication;
import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Presenters.ITaskListPresenterControl;
import com.example.ruireutov.androidtrainingproject.R;
import com.example.ruireutov.androidtrainingproject.Adapters.TaskListAdapter;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class TaskListView extends Fragment implements ITaskListViewControl, IView {
    @Inject
    public TaskListAdapter taskListAdapter;
    @Inject
    public ITaskListPresenterControl presenterControl;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        MainApplication app = (MainApplication) Objects.requireNonNull(getActivity()).getApplicationContext();
        TaskListFragmentComponent component = DaggerTaskListFragmentComponent.builder()
                .presenterControlComponent(app.getPresenterControlComponent())
                .build();
        component.injectTaskListView(this);

        presenterControl.bindView(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_list, container, false);

        ListView list = v.findViewById(R.id.list_tasks);
        list.setAdapter(taskListAdapter);

        v.findViewById(R.id.button_new_task).setOnClickListener(presenterControl.getFloatButtonHandler());

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenterControl.unbindView();
    }

    @Override
    public void setTaskList(List<Task> taskList) {
        taskListAdapter.setTaskList(taskList);
    }

    @Override
    public void showDialogFragment(DialogFragment dialogFragment) {
        FragmentManager fm = getFragmentManager();
        if(fm != null) {
            dialogFragment.show(fm, TaskDialogView.TASK_DIALOG_TAG);
        }
    }
}
