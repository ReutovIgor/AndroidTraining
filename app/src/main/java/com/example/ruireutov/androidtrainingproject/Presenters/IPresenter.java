package com.example.ruireutov.androidtrainingproject.Presenters;

import android.view.View;

import com.example.ruireutov.androidtrainingproject.Views.IView;

public interface IPresenter {
    void bindView(IView view);
    void unbindView();
    View.OnClickListener getFloatButtonHandler();
}
