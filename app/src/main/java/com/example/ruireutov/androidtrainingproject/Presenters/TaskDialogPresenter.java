package com.example.ruireutov.androidtrainingproject.Presenters;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Views.ITaskDialogViewControl;
import com.example.ruireutov.androidtrainingproject.Views.IView;
import com.example.ruireutov.androidtrainingproject.Views.TaskDialogView;

public class TaskDialogPresenter implements ITaskDialogPresenterControl {
    private final static String TASK_DIALOG_PRESENTER_LOG_TAG ="TaskDialogPresenter:";
    private Task task;
    private int taskDialogType;
    private ITaskDialogViewControl viewControl;
    private TaskDialogView.ITaskDialogResponseHandlers responseHandlers;
    private View.OnClickListener applyButtonListener;
    private View.OnClickListener cancelButtonListener;

    public TaskDialogPresenter() {
        applyButtonListener = new ApplyButtonListener();
        cancelButtonListener = new CancelButtonListener();
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
        taskDialogType = bundle.getInt(TaskDialogView.ARGS_TASK_DIALOG_TYPE);
        task = bundle.containsKey(TaskDialogView.ARGS_TASK_DATA) ? bundle.getParcelable(TaskDialogView.ARGS_TASK_DATA) : new Task(-1, "");
        if(task != null) {
            switch (taskDialogType) {
                case TaskDialogView.NEW_TASK_DIALOG:
                    viewControl.setNewTaskDialogType();
                    break;
                case TaskDialogView.UPDATE_TASK_DIALOG:
                    viewControl.setUpdateTaskDialogType(task.getLabel());
                    break;
                case TaskDialogView.DELETE_TASK_DIALOG:
                    viewControl.setDeleteTaskDialogType(task.getLabel());
                    break;
            }
        } else {
            Log.e(TASK_DIALOG_PRESENTER_LOG_TAG, "setDialogArgs: received arguments contained null task, dialog will not be shown");
            viewControl.removeDialogView();
        }
    }

    @Override
    public void setDialogResponseHandlers(TaskDialogView.ITaskDialogResponseHandlers handlers) {
        responseHandlers = handlers;
    }

    @Override
    public View.OnClickListener getApplyButtonListener() {
        return applyButtonListener;
    }

    @Override
    public View.OnClickListener getCancelButtonListener() {
        return cancelButtonListener;
    }

    private class ApplyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(responseHandlers != null) {
                task.setLabel(viewControl.getTaskLabel());
                responseHandlers.applyButtonHandler(taskDialogType, task);
                responseHandlers = null;
            } else {
                Log.e(TASK_DIALOG_PRESENTER_LOG_TAG, "onApplyButtonCLick: no response handlers are passed");
            }
            viewControl.removeDialogView();
        }
    }

    private class CancelButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(responseHandlers != null) {
                responseHandlers.cancelButtonHandler(taskDialogType);
                responseHandlers = null;
            } else {
                Log.e(TASK_DIALOG_PRESENTER_LOG_TAG, "onApplyButtonCLick: no response handlers are passed");
            }
            viewControl.removeDialogView();
        }
    }
}
