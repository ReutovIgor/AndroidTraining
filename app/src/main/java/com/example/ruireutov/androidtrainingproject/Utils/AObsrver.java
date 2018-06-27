package com.example.ruireutov.androidtrainingproject.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class AObsrver implements IObserver{
    private final List<IObserverListener> listeners = new ArrayList<>();

    @Override
    public void addListener(IObserverListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(IObserverListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void removeAll() {
        listeners.clear();
    }

    @Override
    public void onChanged() {
        for (IObserverListener l: listeners) {
            l.onChanged(this);
        }
    }


}
