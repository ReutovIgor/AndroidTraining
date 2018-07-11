package com.example.ruireutov.androidtrainingproject.Views;

import com.example.ruireutov.androidtrainingproject.Presenters.TaskDialogPresenter;

public interface ITaskDialogViewControl extends IView {
    void setTaskDialogHeader(int titleId);
    void bindData (TaskDialogPresenter taskDialogPresenter);
    void removeDialogView();
}
