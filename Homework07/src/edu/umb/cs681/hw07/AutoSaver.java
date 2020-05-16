package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;


public class AutoSaver implements Runnable {

    private File theFile;
    private boolean done = false;
    private ReentrantLock lock;

    AutoSaver(File f) {
        theFile = f;
        lock = new ReentrantLock();
    }
        
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (done) {
                    System.out.printf("\tAutosaver: Interrupted!\n");
                    break;
                }
                System.out.printf("Autosaver: Saving...\n");
                theFile.save();
                Thread.sleep(100);
            }  catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void setDone() {
        done = true;
    }

}
