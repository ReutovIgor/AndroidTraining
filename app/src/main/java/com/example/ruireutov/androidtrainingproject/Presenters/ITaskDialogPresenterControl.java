package com.example.ruireutov.androidtrainingproject.Presenters;

import android.os.Bundle;
import android.view.View;

import com.example.ruireutov.androidtrainingproject.Views.TaskDialogView;

public interface ITaskDialogPresenterControl extends IPresenter {
    void setDialogArgs(Bundle bundle);
    void setDialogResponseHandlers(TaskDialogView.ITaskDialogResponseHandlers handlers);;
}
