package edu.umb.cs681.hw04;

import java.util.stream.Stream;

public class VariThreadedPrimeGenerator {
    public VariThreadedPrimeGenerator(long from, long to, int numThreads) {
        long task_len = (to - from) / numThreads;
        RunnablePrimeGenerator[] genArray = new RunnablePrimeGenerator[numThreads];
        Thread[] threadArray = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            long lo = from + i * task_len;
            long hi = Long.min(to, (i + 1) * task_len);
            // System.out.printf("got to here...");
            RunnablePrimeGenerator gen = new RunnablePrimeGenerator(lo, hi);
            Thread thr = new Thread(gen);
            genArray[i] = gen;
            threadArray[i] = thr;
            thr.start();
        }
        
        for (Thread thr: threadArray) {
            try {
                thr.join();
            } catch (InterruptedException e) {
            }
        }
        // Stream.of(genArray).forEach(gen -> gen.getPrimes().forEach(prime -> {}));
    }
}
