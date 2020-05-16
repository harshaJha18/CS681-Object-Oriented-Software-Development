package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class File {
    private ReentrantLock lock;
    private boolean changed = false;
    private String contents;

    public File(String s) {
        lock = new ReentrantLock();
        contents = s;
    }

    protected void change(String s) {
        lock.lock();
        try {
            contents = s;
            changed = true;
        } finally {
            lock.unlock();
        }
    }
    
    protected void save() {
        lock.lock();
        try {
            if(changed) {
                changed = false;
            }
        } finally {
            lock.unlock();
        }
    }
}
