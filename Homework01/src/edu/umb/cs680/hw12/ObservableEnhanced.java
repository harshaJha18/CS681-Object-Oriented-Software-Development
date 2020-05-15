package edu.umb.cs680.hw12;

import java.util.LinkedList;

public class ObservableEnhanced<T> {

    private LinkedList<ObserverEnhanced<T>> observers;
    private boolean hasChanged;

    public ObservableEnhanced() {
        observers = new LinkedList<ObserverEnhanced<T>>();
    }

    public void addObserver(ObserverEnhanced<T> o) {
        observers.add(o);
    }

    protected LinkedList<ObserverEnhanced<T>> getObservers() {
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

    public void notifyObservers(T event) {
        if (hasChanged) {
            for (ObserverEnhanced<T> observer : observers) {
                observer.update(event);
            }
            clearChanged();
        }
    }
}
