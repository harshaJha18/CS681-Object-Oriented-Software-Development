package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;




public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private volatile boolean done = false;
    private ReentrantLock lock;
    private int latency;
    private long divisor;


    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to, int latency) {
        super(dividend, from, to);
        this.factors = new LinkedList<Long>();
        this.latency = latency; // to test cancellation
        this.lock = new ReentrantLock();
        this.divisor = from;
    }

    public void setDone(){
        setLock();
        try {
            done = true;
        } finally {
            unsetLock();
        }
    }

    public void generatePrimeFactors() {
        while (true) {
            try {
                Thread.sleep(latency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  // pace execution to make cancellation predictable (for testing)
            setLock();
            try {
                if (done || dividend == 1 || divisor > to) {
                    return;
                }
            } finally {
                unsetLock();
            }
            factorizingStep();
        }
    }


    public void factorizingStep() {
        if(divisor > 2 && isEven(divisor)) {
            divisor++;
            return;
        }
        if(dividend % divisor == 0) {
            factors.add(divisor);
            // System.out.printf("\t\tPF: added %d\n", divisor);
            dividend /= divisor;
        } else {
            int increment = (divisor == 2 ? 1 : 2);
            divisor = divisor + increment;
        }
    };

    protected void setLock() {
        lock.lock();
    }

    protected void unsetLock() {
        lock.unlock();
    }


    public boolean isDone() {
        return done;
    }

    public int getLatency() {
        return latency;
    }

    public long getDivisor() {
        return divisor;
    }

    public long getDividend() {
        return dividend;
    }


}



