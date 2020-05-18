package edu.umb.cs681.hw12;

import java.util.HashMap;
import java.nio.file.Path;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter implements AccessCounterInterface {
    HashMap<Path, Integer> data = new HashMap<Path, Integer>();
    private static ReentrantLock lock = new ReentrantLock();

    public void increment(Path path) {
        lock.lock();
        try {
            data.put(path, getCount(path) + 1);            
        } finally {
            lock.unlock();
        }
    }

    public Integer getCount(Path path) {
        lock.lock();
        try {
            return data.getOrDefault(path, 0);
        } finally {
            lock.unlock();
        }
    }

    public void printData() {
        lock.lock();
        try {
            System.out.printf("\nAccessCounter Data:\n\n");
            data.forEach((key, value) -> 
                    System.out.printf("\t%s served %d times\n",
                                      key, value));
        } finally {
            lock.unlock();
        }
    }

}
