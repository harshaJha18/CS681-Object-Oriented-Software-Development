package edu.umb.cs680.hw11;

import java.util.LinkedList;

public class MyObservable {

    private LinkedList<MyObserver> observers;
    private boolean hasChanged;

    public MyObservable() {
        observers = new LinkedList<MyObserver>();
    }

    public void addObserver(MyObserver o) {
        observers.add(o);
    }

    protected LinkedList<MyObserver> getObservers() {
        return observers;
    }

    public void setChanged() {
        hasChanged = true;
    }

    public void clearChanged() {
        hasChanged = false;
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public void notifyObservers(Object obj) {
        if (hasChanged) {
            for (MyObserver observer : observers) {
                observer.update(obj);
            }
            clearChanged();
        }
    }
}
