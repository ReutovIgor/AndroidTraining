package com.example.ruireutov.androidtrainingproject.Views;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ruireutov.androidtrainingproject.R;

public class TaskDialogView extends DialogFragment{
    private static final int NEW_TASK_DIALOG = 0;
    private static final int UPDATE_TASK_DIALOG = 1;
    private static final int DELETE_TASK_DIALOG = 2;

    int dialogType;
    EditText taskName;
    Button applyButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_dialog, container, false);

        taskName = v.findViewById(R.id.text_task_dialog_description);
        applyButton = v.findViewById(R.id.button_task_dialog_apply);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskListView tv = (TaskListView) getTargetFragment();
                switch (dialogType) {
                    case NEW_TASK_DIALOG:
                        tv.onNewTask(taskName.getText().toString());
                        break;
                    case UPDATE_TASK_DIALOG:
//                        tv.onUpdateTask(getArguments().getInt("id"), taskName.getText().toString());
                        break;
                    case DELETE_TASK_DIALOG:
//                        tv.onDeleteTask(getArguments().getInt("id"));
                        break;
                }
                dismiss();
            }
        });

        v.findViewById(R.id.button_task_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Bundle args = getArguments();

        switch (dialogType) {
            case NEW_TASK_DIALOG:
                getDialog().setTitle(getString(R.string.task_dialog_description_title_create_task));
                taskName.setText("");
                taskName.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                taskName.setFocusable(true);
                applyButton.setText(getString(R.string.task_dialog_button_create_task));
                break;
            case UPDATE_TASK_DIALOG:
                getDialog().setTitle(getString(R.string.task_dialog_description_title_update_task));
                taskName.setText(args.getString("description"));
                taskName.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                taskName.setFocusable(true);
                applyButton.setText(getString(R.string.task_dialog_button_update_task));
                break;
            case DELETE_TASK_DIALOG:
                getDialog().setTitle(getString(R.string.task_dialog_description_title_delete_task));
                taskName.setText(args.getString("description"));
                taskName.setInputType(InputType.TYPE_NULL);
                taskName.setFocusable(false);
                applyButton.setText(getString(R.string.task_dialog_button_delete_task));
                break;
        }

        return v;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void showNewTaskDialog(FragmentManager fragmentManager) {
        dialogType = NEW_TASK_DIALOG;
        show(fragmentManager, "NewTaskDialog");
    }

    public void showUpdateTaskDialog(FragmentManager fragmentManager) {
        dialogType = UPDATE_TASK_DIALOG;
        show(fragmentManager, "UpdateTaskDialog");
    }

    public void showDeleteTaskDialog(FragmentManager fragmentManager) {
        dialogType = DELETE_TASK_DIALOG;
        show(fragmentManager, "DeleteTaskDialog");
    }
}
