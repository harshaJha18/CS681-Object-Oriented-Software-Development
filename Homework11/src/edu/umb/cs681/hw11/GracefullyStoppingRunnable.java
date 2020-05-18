package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;


public class GracefullyStoppingRunnable implements Runnable {

    private boolean done = false;
    private ReentrantLock lock;
    private Runnable methodToRun;


    private GracefullyStoppingRunnable() {
        lock = new ReentrantLock();
    }

    public void run() {
        while (true) {
            setLock();
            try {
                if (done) {
                    // System.out.printf("\tRequestHandler %d: stopping\n", getThread().getId());
                    break;
                }
            } finally {
                unsetLock();
            };
            methodToRun.run();
            try {
                Thread.sleep(100);            
            } catch (InterruptedException e) {
                continue;
            }
        }
    }

    public static GracefullyStoppingRunnable fromRunnable(Runnable runnable) {
        GracefullyStoppingRunnable newRunnable = new GracefullyStoppingRunnable();
        newRunnable.methodToRun = runnable;
        return newRunnable;
    }

    public Runnable toRunnable() {
        return () -> run();
    }
    
    
    protected void setLock() {
        lock.lock();
    }

    protected void unsetLock() {
        lock.unlock();
    }    

    public Thread getThread() {
        return Thread.currentThread();
    }

    public void gracefulStop() {
        setLock();
        try {
            done = true;
            getThread().interrupt();
        } finally {
            unsetLock();
        }
    }
    
}
