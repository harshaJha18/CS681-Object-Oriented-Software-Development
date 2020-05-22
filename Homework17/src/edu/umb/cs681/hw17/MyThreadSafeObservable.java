package edu.umb.cs681.hw17;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.umb.cs680.hw11.MyObserver;

public class MyThreadSafeObservable {

    private ConcurrentLinkedQueue<MyObserver> observers = new ConcurrentLinkedQueue<MyObserver>();
    private AtomicBoolean hasChanged = new AtomicBoolean(false);

    public void addObserver(MyObserver o) {
        observers.add(o);
    }

    protected ConcurrentLinkedQueue<MyObserver> getObservers() {
        return observers;
    }

    public void setChanged() {
        hasChanged.set(true);
    }

    public void clearChanged() {
        hasChanged.set(false);
    }

    public boolean hasChanged() {
        return hasChanged.get();
    }

    public void notifyObservers() {
        for (MyObserver observer : observers) {
            observer.update(observer);
        }
    }
}
