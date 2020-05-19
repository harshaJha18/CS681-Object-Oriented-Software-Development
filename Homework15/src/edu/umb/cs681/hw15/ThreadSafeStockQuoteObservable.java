package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import edu.umb.cs680.hw11.StockEvent;

public class ThreadSafeStockQuoteObservable extends MyThreadSafeObservable {

    private HashMap<String, Float> tickerToQuote = new HashMap<String, Float>();
    private ReentrantLock lockTQ = new ReentrantLock();

    public void changeQuote(String ticker, Float quote) {
        lockTQ.lock();
        try {
            tickerToQuote.put(ticker, quote);
            setChanged();
            notifyObservers(new StockEvent(ticker, quote));
        } finally {
            lockTQ.unlock();
        }
    }

    public Float getQuote(String ticker) {
        lockTQ.lock();
        try {
            return tickerToQuote.get(ticker);
        } finally {
            lockTQ.unlock();
        }
    }

}
