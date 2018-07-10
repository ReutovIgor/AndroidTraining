package com.example.ruireutov.androidtrainingproject.Views;

import android.databinding.ObservableField;

import com.example.ruireutov.androidtrainingproject.Model.TaskDialogViewModel;

public interface ITaskDialogViewControl extends IView {
    String getTaskLabel();
    void setNewTaskDialogType();
    void setUpdateTaskDialogType();
    void setDeleteTaskDialogType();
    void bindData (TaskDialogViewModel taskDialogViewModel);
    void removeDialogView();
}
