package com.example.ruireutov.androidtrainingproject.Views;

public interface ITaskDialogViewControl extends IView {
    String getTaskLabel();
    void setNewTaskDialogType();
    void setUpdateTaskDialogType(String label);
    void setDeleteTaskDialogType(String label);
    void removeDialogView();
}
