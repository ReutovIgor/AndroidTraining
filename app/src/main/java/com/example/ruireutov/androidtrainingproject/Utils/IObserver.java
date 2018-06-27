package com.example.ruireutov.androidtrainingproject.Utils;


public interface IObserver {
    interface IObserverListener {
        void onChanged(IObserver some);
    }
    void addListener(IObserverListener listener);
    void removeListener(IObserverListener listener);
    void removeAll();

    void onChanged();
}
