package edu.umb.cs681.hw21;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {

    ReentrantLock lockObs = new ReentrantLock();
    protected LinkedList<Observer> observers = new LinkedList<>();
    protected boolean changed;

    protected Observable () {
        changed = false;
    }

    protected void setChanged() {
        lockObs.lock();
        try {
            this.changed = true;
        } finally {
            lockObs.unlock();
        }

    }
    protected void clearChanged() {
        lockObs.lock();
        try {
            this.changed = false;
        } finally {
            lockObs.unlock();
        }

    }
    public boolean hasChanged() {
        lockObs.lock();
        try {
            return changed;
        } finally {
            lockObs.unlock();
        }
    }


    public void addObserver(Observer observer) throws Exception {
        lockObs.lock();
        try {
            observers.add(observer);
        } finally {
            lockObs.unlock();
        }

    }


    public void deleteObserver(Observer observer) throws Exception {
        lockObs.lock();
        try {
            observers.remove(observer);
        } finally {
            lockObs.unlock();
        }

    }


    public void notifyObservers(Object obj) {
        Object[] observerArray = null;
        lockObs.lock();
        
        try {
            if(this.hasChanged()) {
                observerArray = observers.toArray();
            }
            this.clearChanged();
        } finally {
            lockObs.unlock();
        }

        for (int i = 0; i < observerArray.length; i++) {
            ((Observer)observerArray[i]).update(this, obj);
        }



    }
}
