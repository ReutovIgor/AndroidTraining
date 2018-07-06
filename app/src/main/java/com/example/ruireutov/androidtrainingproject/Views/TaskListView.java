package com.example.ruireutov.androidtrainingproject.Views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Presenters.ITaskListPresenterControl;
import com.example.ruireutov.androidtrainingproject.R;
import com.example.ruireutov.androidtrainingproject.Adapters.TaskListAdapter;

import java.util.List;

public class TaskListView extends Fragment implements ITaskListViewControl, IView {

    private TaskListAdapter taskListAdapter;
    private ITaskListPresenterControl presenterControl;

    public TaskListView() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_list, container, false);

        ListView list = v.findViewById(R.id.list_tasks);
        taskListAdapter = new TaskListAdapter(presenterControl.getAdapterItemHandlers());
        list.setAdapter(taskListAdapter);

        v.findViewById(R.id.button_new_task).setOnClickListener(presenterControl.getFloatButtonHandler());


//        presenterControl = (ITaskListPresenterControl) ((MainApplication)getActivity().getApplicationContext()).getTaskListPresenter();
//        presenterControl.bindView(this);

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
