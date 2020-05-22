package edu.umb.cs681.hw09;

import java.util.LinkedList;

import edu.umb.cs681.hw06.BaseFactorizer;
import edu.umb.cs681.hw06.RunnableCancellablePrimeFactorizer;

public class GracefulFactorizer extends BaseFactorizer {

    LinkedList<RunnableCancellablePrimeFactorizer> runnables;
    LinkedList<Thread> threads;

    protected GracefulFactorizer(long dividend, int numThreads, int latency) {

        super(dividend, numThreads, latency);

        this.runnables = new LinkedList<RunnableCancellablePrimeFactorizer>();
        this.threads = new LinkedList<Thread>();
        
        long portionSize = ((long)Math.sqrt(dividend) - 1) / numThreads + 1;

        for (long i = 1; i <= Math.sqrt(dividend); i += portionSize) {
            RunnableCancellableInterruptiblePrimeFactorizer runnable
                = new RunnableCancellableInterruptiblePrimeFactorizer(dividend, i + 1, i + portionSize, latency);
            runnables.add(runnable);

            Thread thread = new Thread(runnable);
            threads.add(thread);

            System.out.printf("\t\tSpawning thread %d to scan from %d to %d\n",
                              thread.getId(), i+1, i + portionSize);
        }
    }

    private void setDoneAll() {
        runnables.forEach( (r) -> r.setDone());
        threads.forEach( (t) -> t.interrupt());
       
        System.out.printf("\t\tStopping all threads...\n");
    }

    
    public LinkedList<RunnableCancellablePrimeFactorizer> getRunnables() {
        return (LinkedList<RunnableCancellablePrimeFactorizer>) this.runnables;
    }

    public LinkedList<Thread> getThreads() {
        return this.threads;
    }

}
