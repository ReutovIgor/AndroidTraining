package com.example.ruireutov.androidtrainingproject.Presenters;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Model.TaskDialogViewModel;
import com.example.ruireutov.androidtrainingproject.R;
import com.example.ruireutov.androidtrainingproject.Views.ITaskDialogViewControl;
import com.example.ruireutov.androidtrainingproject.Views.IView;
import com.example.ruireutov.androidtrainingproject.Views.TaskDialogView;

public class TaskDialogPresenter extends BaseObservable implements ITaskDialogPresenterControl {
    private final static String TASK_DIALOG_PRESENTER_LOG_TAG ="TaskDialogPresenter:";
    private Task task;
    private int taskDialogType;
    private int applyButtonR;

    private TaskDialogViewModel taskDialogViewModel;
    private ITaskDialogViewControl viewControl;
    private TaskDialogView.ITaskDialogResponseHandlers responseHandlers;

    public TaskDialogPresenter() {
        taskDialogViewModel = new TaskDialogViewModel(TaskDialogView.NEW_TASK_DIALOG, null);
    }

    @Bindable
    public Task getTask() {
        return task;
    }

    @Bindable
    public int getTaskDialogType() {
        return taskDialogType;
    }

    @Bindable
    public int getApplyButtonR() {
        return applyButtonR;
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
        Task taskData = bundle.containsKey(TaskDialogView.ARGS_TASK_DATA) ? bundle.getParcelable(TaskDialogView.ARGS_TASK_DATA) : null;
        task = taskData != null ? taskData : new Task(-1, "");
        taskDialogType = bundle.containsKey(TaskDialogView.ARGS_TASK_DIALOG_TYPE) ?  bundle.getInt(TaskDialogView.ARGS_TASK_DIALOG_TYPE) : TaskDialogView.NEW_TASK_DIALOG;

        int titleId;
        switch (taskDialogType) {
            case TaskDialogView.UPDATE_TASK_DIALOG:
                applyButtonR = R.string.task_dialog_button_update_task;
                titleId = R.string.task_dialog_description_title_update_task;
                break;
            case TaskDialogView.DELETE_TASK_DIALOG:
                applyButtonR = R.string.task_dialog_button_delete_task;
                titleId = R.string.task_dialog_description_title_delete_task;
                break;
            default:
                applyButtonR = R.string.task_dialog_button_create_task;
                titleId = R.string.task_dialog_description_title_create_task;
                break;
        }

        viewControl.setTaskDialogHeader(titleId);
        viewControl.bindData(this);
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
