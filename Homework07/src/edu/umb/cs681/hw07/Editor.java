package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class Editor implements Runnable {

    private File openFile;
    private boolean done = false;
    private ReentrantLock lock;

    Editor(File f) {
        openFile = f;
        lock = new ReentrantLock();
    }

    public void run() {
        
        while(true){
            lock.lock();
            try {
                if(done){ 
                    System.out.println("\tEditor: Interrupted!");
                    break;
                }
                String newContents = RandomString.generate();
                System.out.printf("\tEditor: Changing file contents to %s...\n",
                                  newContents);
                openFile.change(newContents);
                System.out.printf("\tEditor: Saving %s\n",newContents);
                openFile.save();
                Thread.sleep(200);            
            } catch (InterruptedException e) {
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
