package edu.umb.cs681.hw14;

class EntranceHandler implements Runnable {
    private AdmissionMonitor monitor;
    public void run() {
        monitor.enter();
    }
}
