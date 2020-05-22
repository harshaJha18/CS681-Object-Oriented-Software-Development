package edu.umb.cs681.hw09;

import edu.umb.cs681.hw06.RunnableCancellablePrimeFactorizer;


public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {

    RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to, int latency) {
        super(dividend, from, to, latency);
    }

    public void generatePrimeFactors() {
        while (true) {
            this.setLock();
            try {
                if (this.isDone() || this.getDividend() == 1 || this.getDivisor() > to) {
                    return;
                }
            } finally {
                this.unsetLock();
            }
            this.factorizingStep();
           
            try {
                Thread.sleep(this.getLatency());
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

}


