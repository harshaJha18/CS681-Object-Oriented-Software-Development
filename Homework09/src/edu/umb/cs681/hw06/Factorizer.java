package edu.umb.cs681.hw06;

import java.util.LinkedList;
import java.util.ListIterator;

public class Factorizer extends BaseFactorizer {

    LinkedList<RunnableCancellablePrimeFactorizer> runnables;
    LinkedList<Thread> threads;

    Factorizer(long dividend, int numThreads, int latency) {

        super(dividend, numThreads, latency);

        this.runnables = new LinkedList<RunnableCancellablePrimeFactorizer>();
        this.threads = new LinkedList<Thread>();
        
        long portionSize = ((long)Math.sqrt(dividend) - 1) / numThreads + 1;
        
        for (long i = 1; i <= Math.sqrt(dividend); i += portionSize) {
            RunnableCancellablePrimeFactorizer runnable
                = new RunnableCancellablePrimeFactorizer
                (dividend, i + 1, i + portionSize, latency);
            this.runnables.add(runnable);

            Thread thread = new Thread(runnable);
            this.threads.add(thread);

            System.out.printf("\t\tSpawning thread %d to scan from %d to %d\n",
                              thread.getId(), i+1, i + portionSize);
        }

    }

    public LinkedList<RunnableCancellablePrimeFactorizer> getRunnables() {
        return this.runnables;
    }

    public LinkedList<Thread> getThreads() {
        return this.threads;
    }

}



