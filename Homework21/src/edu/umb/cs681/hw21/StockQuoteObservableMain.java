package edu.umb.cs681.hw21;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservableMain extends Observable {

    HashMap<String, Float> stockQuote = new HashMap<>();
    static boolean firstObserverCalled = false;
    static boolean secondObserverCalled = false;
    public HashMap<String, Float> getStockQuote() {
        return stockQuote;
    }

    ReentrantLock stockLock = new ReentrantLock();

    public void changeQuote(String ticker, Float quote) {

        stockLock.lock();
        try {
            stockQuote.replace(ticker, quote);
            this.setChanged();
            this.notifyObservers(stockQuote);
        } finally {
            stockLock.unlock();
        }


    }

    public void setQuote(String ticker, Float quote) {

        stockLock.lock();
        try {
            stockQuote.put(ticker, quote);
        } finally {
            stockLock.unlock();
        }

    }


    public static void main(String[] args) throws Exception {
        StockQuoteObservableMain observable = new StockQuoteObservableMain();
        System.out.printf("*************CS681 Homework 21*************\n");
        observable.addObserver( (Observable o, Object obj)-> {
                    System.out.println("\nFirst Observer :" + obj);
                    firstObserverCalled = true;
                } );
        observable.addObserver( (Observable o, Object obj)-> {
                    System.out.println("\nSecond Observer :" + obj);

                } );

        observable.setQuote("JDKJNX", 15f);
        observable.setQuote("QOIQUW", 24f);
        observable.setQuote("WYBXKM", 16f);
        observable.setQuote("NZNWJI", 36f);
        observable.setQuote("PWQDIW", 31f);
        observable.setQuote("MXWOQP", 13f);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new StockQuoteDataHandler(observable));
        executor.execute(new StockQuoteDataHandler(observable));
        
        Thread.sleep(5);
        
        executor.shutdown();

    }
}

class StockQuoteDataHandler implements  Runnable {

    private static AtomicBoolean done = new AtomicBoolean();
    StockQuoteObservableMain observable;

    public static void setDone() {
        done.set(true);
    }

    StockQuoteDataHandler(StockQuoteObservableMain observable) {
        done.set(false);
        this.observable = observable;
    }

    @Override
    public void run() {
    	Random r = new Random();
        observable.changeQuote("HAKHHS", r.nextFloat());
    }
}