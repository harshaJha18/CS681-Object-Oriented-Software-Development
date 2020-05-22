package edu.umb.cs681.hw16;

import java.util.concurrent.ConcurrentHashMap;
import java.nio.file.Path;
import java.util.concurrent.locks.ReentrantLock;

import edu.umb.cs681.hw12.AccessCounterInterface;

public class ThreadSafeAccessCounter implements AccessCounterInterface {
    ConcurrentHashMap<Path, Integer> data = new ConcurrentHashMap<Path, Integer>();
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
            System.out.printf("\nAccessCounter.printData:\n");
            data.forEach((key, value) -> 
                    System.out.printf("\t%s served %d times\n",
                                      key, value));
        } finally {
            lock.unlock();
        }
    }

}
