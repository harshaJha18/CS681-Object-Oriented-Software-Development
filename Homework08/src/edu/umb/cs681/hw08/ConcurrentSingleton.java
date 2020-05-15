package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {

    private static ConcurrentSingleton instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static int numConstructorCalls = 0;
    
    private ConcurrentSingleton() {
        numConstructorCalls += 1;
    }

    public static ConcurrentSingleton getInstance() {

        lock.lock();
        try {
            if (instance == null) {
                instance = new ConcurrentSingleton();
                
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public static void printNumConstructorCalls() {
        System.out.printf("\nConcurrent Singleton: number of calls to my constructor is %d\n",
                          numConstructorCalls);
    }
}
