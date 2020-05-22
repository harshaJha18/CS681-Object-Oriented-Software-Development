package edu.umb.cs681.hw06;

import java.util.LinkedList;

public abstract class BaseFactorizer {

    long dividend;
    int numThreads;
    int latency;
    LinkedList<Long> factorsFound;
    boolean finished = false;

    protected BaseFactorizer(long dividend, int numThreads, int latency) {

        this.dividend = dividend;
        this.numThreads = numThreads;
        this.latency = latency;
        this.factorsFound = new LinkedList<Long>();

        System.out.printf("\nFactorization of %d with %d threads\n",
                          dividend, numThreads);

    }

    public abstract LinkedList<RunnableCancellablePrimeFactorizer> getRunnables();

    public abstract LinkedList<Thread> getThreads();

    public void run() {
            
        this.getThreads().forEach( (t) -> {
                t.start();
                // System.out.printf("\t\tStarting process in thread %d\n", t.getId());
            });
        
    }    

    public void stop() {
        setDoneAll();
        joinAll();
        assembleFactors();
    }

    public void finish() {
        joinAll();
        finished = true;
        assembleFactors();
    }

    public LinkedList<Long> getResults() {
        return factorsFound;
    }

    public void printResults() {
        System.out.println("\nFactors found: " + factorsFound + "\n");
    }

    void assembleFactors() {
        getFromFactorizers();
        addLastFactor();
    }

    void getFromFactorizers() {
      
        getRunnables().stream().forEach((runnable) -> {
            runnable.getPrimeFactors()
            .forEach((maybeNext) -> {
                    {if (toAdd(maybeNext)) {
                        factorsFound.add(maybeNext);
                        }
                    }
                });
            });
    }

    boolean toAdd(long maybeNext) {
        return factorsFound
            .stream()
            .allMatch((foundFactor) ->
                      foundFactor == maybeNext
                      || maybeNext % foundFactor != 0);
    }

    void addLastFactor() {
        long toAdd = dividend;
        if (finished) {
            for (long f: factorsFound) {
                toAdd /= f;
            }
            if (toAdd > 1) {
                factorsFound.add(toAdd);
            }
        }
    }

    
    void joinAll() {
        getThreads().forEach( (t) -> {
                try {
                    t.join();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            });
        
    }

    void setDoneAll() {
        getRunnables().forEach( (r) -> r.setDone());
        System.out.printf("\t\tStopping all threads...\n");

    }


}
