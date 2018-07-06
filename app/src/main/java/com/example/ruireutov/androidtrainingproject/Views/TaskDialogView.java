package com.example.ruireutov.androidtrainingproject.Views;

import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Presenters.ITaskDialogPresenterControl;
import com.example.ruireutov.androidtrainingproject.Presenters.TaskDialogPresenter;
import com.example.ruireutov.androidtrainingproject.R;

public class TaskDialogView extends DialogFragment implements ITaskDialogViewControl{
    public static final String TASK_DIALOG_TAG = "TaskDialogView";

    public static final int NEW_TASK_DIALOG = 0;
    public static final int UPDATE_TASK_DIALOG = 1;
    public static final int DELETE_TASK_DIALOG = 2;

    public static final String ARGS_TASK_DIALOG_TYPE = "type";
    public static final String ARGS_TASK_DATA = "task";

    ITaskDialogPresenterControl presenterControl;
    EditText taskName;
    Button applyButton;

    public interface ITaskDialogResponseHandlers {
        void applyButtonHandler(int type, Task task);
        void cancelButtonHandler(int type);
    }

    public TaskDialogView () {
        presenterControl = new TaskDialogPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_dialog, container, false);

        presenterControl.bindView(this);
        presenterControl.setDialogArgs(getArguments());

        taskName = v.findViewById(R.id.text_task_dialog_description);
        applyButton = v.findViewById(R.id.button_task_dialog_apply);
        applyButton.setOnClickListener(presenterControl.getApplyButtonListener());
        v.findViewById(R.id.button_task_dialog_cancel).setOnClickListener(presenterControl.getCancelButtonListener());

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getTaskLabel() {
        return taskName.getText().toString();
    }

    @Override
    public void setNewTaskDialogType() {
        getDialog().setTitle(getString(R.string.task_dialog_description_title_create_task));
        taskName.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
        taskName.setFocusable(true);
        taskName.setText("");
        applyButton.setText(getString(R.string.task_dialog_button_create_task));
    }

    @Override
    public void setUpdateTaskDialogType(String label) {
        getDialog().setTitle(getString(R.string.task_dialog_description_title_update_task));
        taskName.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
        taskName.setFocusable(true);
        taskName.setText(label);
        applyButton.setText(getString(R.string.task_dialog_button_update_task));
    }

    @Override
    public void setDeleteTaskDialogType(String label) {
        getDialog().setTitle(getString(R.string.task_dialog_description_title_delete_task));
        taskName.setInputType(InputType.TYPE_NULL);
        taskName.setFocusable(false);
        taskName.setText(label);
        applyButton.setText(getString(R.string.task_dialog_button_delete_task));
    }

    @Override
    public void removeDialogView() {
        dismiss();
    }

    public void setTaskDialogViewHandlers(ITaskDialogResponseHandlers handlers) {
        presenterControl.setDialogResponseHandlers(handlers);
    }
}
