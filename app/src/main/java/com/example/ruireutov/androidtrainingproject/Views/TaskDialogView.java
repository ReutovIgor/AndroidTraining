package com.example.ruireutov.androidtrainingproject.Views;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruireutov.androidtrainingproject.Components.DaggerTaskDialogFragmentComponent;
import com.example.ruireutov.androidtrainingproject.Components.TaskDialogFragmentComponent;
import com.example.ruireutov.androidtrainingproject.MainApplication;
import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Presenters.ITaskDialogPresenterControl;
import com.example.ruireutov.androidtrainingproject.Presenters.TaskDialogPresenter;
import com.example.ruireutov.androidtrainingproject.R;
import com.example.ruireutov.androidtrainingproject.databinding.FragmentTaskDialogBinding;

import java.util.Objects;

import javax.inject.Inject;

public class TaskDialogView extends DialogFragment implements ITaskDialogViewControl{
    public static final String TASK_DIALOG_TAG = "TaskDialogView";

    public static final int NEW_TASK_DIALOG = 0;
    public static final int UPDATE_TASK_DIALOG = 1;
    public static final int DELETE_TASK_DIALOG = 2;

    public static final String ARGS_TASK_DIALOG_TYPE = "type";
    public static final String ARGS_TASK_DATA = "task";

    private ITaskDialogResponseHandlers handlers;
    private FragmentTaskDialogBinding binding;

    @Inject
    public ITaskDialogPresenterControl presenterControl;

    public interface ITaskDialogResponseHandlers {
        void applyButtonHandler(int type, Task task);
        void cancelButtonHandler(int type);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        MainApplication app = (MainApplication) Objects.requireNonNull(getActivity()).getApplicationContext();

        TaskDialogFragmentComponent component = DaggerTaskDialogFragmentComponent.builder()
                .presenterControlComponent(app.getPresenterControlComponent())
                .build();
        component.injectTaskDialogView(this);

        presenterControl.bindView(this);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_dialog, container, false);
        View v = binding.getRoot();

        presenterControl.setDialogArgs(getArguments());
        presenterControl.setDialogResponseHandlers(handlers);

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenterControl.unbindView();
    }

    @Override
    public void setTaskDialogHeader(int titleId) {
        getDialog().setTitle(titleId);
    }

    @Override
    public void bindData(TaskDialogPresenter taskDialogPresenter) {
        binding.setTaskDialogModel(taskDialogPresenter);
    }

    @Override
    public void removeDialogView() {
        dismiss();
    }

    public void setTaskDialogViewHandlers(ITaskDialogResponseHandlers handlers) {
        this.handlers = handlers;
    }
}
