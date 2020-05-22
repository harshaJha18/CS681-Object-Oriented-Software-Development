package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.umb.cs680.hw11.MyObserver;

public class MyThreadSafeObservable {

    private LinkedList<MyObserver> observers = new LinkedList<MyObserver>();
    private AtomicBoolean hasChanged = new AtomicBoolean(false);
    private ReentrantLock lockObs = new ReentrantLock();

    public void addObserver(MyObserver o) {
        lockObs.lock();
        try {
            observers.add(o);
        } finally {
            lockObs.unlock();
        }
    }

    protected LinkedList<MyObserver> getObservers() {
        lockObs.lock();
        try {
        return observers;
        } finally {
            lockObs.unlock();
        }
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

    public void notifyObservers(Object obj) {
        if (!hasChanged()) {
            return;
        }
        lockObs.lock();
        Object[] observersLocal;
        try {
            observersLocal = observers.toArray();
            clearChanged();
        } finally {
            lockObs.unlock();
        }
        for (Object observer : observersLocal) {
            ((MyObserver)observer).update(obj);
        }
    }
}
