package com.example.ruireutov.androidtrainingproject.Presenters;

import android.os.Bundle;
import android.view.View;

import com.example.ruireutov.androidtrainingproject.Adapters.TaskListAdapter;
import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Repository.TaskRepositoryControl;
import com.example.ruireutov.androidtrainingproject.Views.ITaskListViewControl;
import com.example.ruireutov.androidtrainingproject.Utils.IObserver;
import com.example.ruireutov.androidtrainingproject.Views.IView;
import com.example.ruireutov.androidtrainingproject.Views.TaskDialogView;

public class TaskListPresenter implements ITaskListPresenterControl {
    private ITaskListViewControl viewControl;
    private TaskRepositoryControl taskRepositoryControl;

    private TaskListItemEvents itemEvents;
    private TaskDialogViewResponseHandlers responseHandlers;
    private NewTaskButtonListener newTaskButtonListener;

    public TaskListPresenter(TaskRepositoryControl taskRepositoryControl) {
        this.taskRepositoryControl = taskRepositoryControl;
        taskRepositoryControl.addListener(this::onChanged);

        itemEvents = new TaskListItemEvents();
        responseHandlers = new TaskDialogViewResponseHandlers();
        newTaskButtonListener = new NewTaskButtonListener();
    }

    @Override
    public TaskListAdapter.TaskAdapterCallback getAdapterItemHandlers() {
        return itemEvents;
    }

    @Override
    public View.OnClickListener getFloatButtonHandler() {
        return newTaskButtonListener;
    }

    @Override
    public void bindView(IView view) {
        this.viewControl = (ITaskListViewControl) view;
        viewControl.setTaskList(taskRepositoryControl.getTasks());
    }

    @Override
    public void unbindView() {
        this.viewControl = null;
    }

    private void onChanged(IObserver some) {
        if(viewControl != null)
            viewControl.setTaskList(taskRepositoryControl.getTasks());
    }

    private class TaskListItemEvents implements TaskListAdapter.TaskAdapterCallback {

        @Override
        public void onTaskChanged(Task task) {
            taskRepositoryControl.updateTask(task);
        }

        @Override
        public void onTaskUpdate(Task task) {
            Bundle args = new Bundle();
            args.putInt(TaskDialogView.ARGS_TASK_DIALOG_TYPE, TaskDialogView.UPDATE_TASK_DIALOG);
            args.putParcelable(TaskDialogView.ARGS_TASK_DATA, task);
            TaskDialogView dialogView = new TaskDialogView();
            dialogView.setArguments(args);
            dialogView.setTaskDialogViewHandlers(responseHandlers);
            viewControl.showDialogFragment(dialogView);
        }

        @Override
        public void onTaskDelete(Task task) {
            Bundle args = new Bundle();
            args.putInt(TaskDialogView.ARGS_TASK_DIALOG_TYPE, TaskDialogView.DELETE_TASK_DIALOG);
            args.putParcelable(TaskDialogView.ARGS_TASK_DATA, task);
            TaskDialogView dialogView = new TaskDialogView();
            dialogView.setArguments(args);
            dialogView.setTaskDialogViewHandlers(responseHandlers);
            viewControl.showDialogFragment(dialogView);
        }
    }

    private class TaskDialogViewResponseHandlers implements TaskDialogView.ITaskDialogResponseHandlers{
        @Override
        public void applyButtonHandler(int type, Task task) {
            switch (type) {
                case TaskDialogView.NEW_TASK_DIALOG:
                    taskRepositoryControl.addTask(task.getLabel());
                    break;
                case TaskDialogView.UPDATE_TASK_DIALOG:
                    taskRepositoryControl.updateTask(task);
                    break;
                case TaskDialogView.DELETE_TASK_DIALOG:
                    taskRepositoryControl.deleteTask(task);
                    break;
            }
        }

        @Override
        public void cancelButtonHandler(int type) {}
    }

    private class NewTaskButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Bundle args = new Bundle();
            args.putInt(TaskDialogView.ARGS_TASK_DIALOG_TYPE, TaskDialogView.NEW_TASK_DIALOG);
            TaskDialogView dialogView = new TaskDialogView();
            dialogView.setArguments(args);
            dialogView.setTaskDialogViewHandlers(responseHandlers);
            viewControl.showDialogFragment(dialogView);
        }
    }
}
