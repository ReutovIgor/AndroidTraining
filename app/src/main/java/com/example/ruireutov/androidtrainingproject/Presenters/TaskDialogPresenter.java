package com.example.ruireutov.androidtrainingproject.Presenters;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ruireutov.androidtrainingproject.Model.TaskDialogViewModel;
import com.example.ruireutov.androidtrainingproject.Views.ITaskDialogViewControl;
import com.example.ruireutov.androidtrainingproject.Views.IView;
import com.example.ruireutov.androidtrainingproject.Views.TaskDialogView;

public class TaskDialogPresenter implements ITaskDialogPresenterControl {
    private final static String TASK_DIALOG_PRESENTER_LOG_TAG ="TaskDialogPresenter:";
    private TaskDialogViewModel taskDialogViewModel;
    private ITaskDialogViewControl viewControl;
    private TaskDialogView.ITaskDialogResponseHandlers responseHandlers;

    public TaskDialogPresenter() {
        taskDialogViewModel = new TaskDialogViewModel(TaskDialogView.NEW_TASK_DIALOG, null);
    }

    @Override
    public void bindView(IView view) {
        viewControl = (ITaskDialogViewControl) view;
    }

    @Override
    public void unbindView() {
        viewControl = null;
    }

    @Override
    public View.OnClickListener getFloatButtonHandler() {
        return null;
    }

    @Override
    public void setDialogArgs(Bundle bundle) {
        int taskDialogType = bundle.getInt(TaskDialogView.ARGS_TASK_DIALOG_TYPE);
        taskDialogViewModel = new TaskDialogViewModel(
                taskDialogType,
                bundle.containsKey(TaskDialogView.ARGS_TASK_DATA)  ? bundle.getParcelable(TaskDialogView.ARGS_TASK_DATA) : null);

        switch (taskDialogType) {
            case TaskDialogView.NEW_TASK_DIALOG:
                viewControl.setNewTaskDialogType();
                break;
            case TaskDialogView.UPDATE_TASK_DIALOG:
                viewControl.setUpdateTaskDialogType();
                break;
            case TaskDialogView.DELETE_TASK_DIALOG:
                viewControl.setDeleteTaskDialogType();
                break;
        }
        viewControl.bindData(taskDialogViewModel);
    }

    @Override
    public void setDialogResponseHandlers(TaskDialogView.ITaskDialogResponseHandlers handlers) {
        responseHandlers = handlers;
    }

    public void onApplyAction() {
        if(responseHandlers != null) {
            responseHandlers.applyButtonHandler(taskDialogViewModel.getDialogType(), taskDialogViewModel.getTaskData());
            responseHandlers = null;
        } else {
            Log.e(TASK_DIALOG_PRESENTER_LOG_TAG, "onApplyButtonCLick: no response handlers are passed");
        }
        viewControl.removeDialogView();
    }

    public void onCancelAction() {
        if(responseHandlers != null) {
            responseHandlers.cancelButtonHandler(taskDialogViewModel.getDialogType());
            responseHandlers = null;
        } else {
            Log.e(TASK_DIALOG_PRESENTER_LOG_TAG, "onApplyButtonCLick: no response handlers are passed");
        }
        viewControl.removeDialogView();
    }
}
