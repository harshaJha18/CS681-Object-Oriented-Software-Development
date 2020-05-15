package edu.umb.cs680.hw11;

import java.util.HashMap;

public class StockQuoteObservable extends MyObservable {

    private HashMap<String, Float> tickerToQuote;

    public StockQuoteObservable() {
        tickerToQuote = new HashMap<String, Float>();
    }


    public void changeQuote(String ticker, Float quote) {
        tickerToQuote.put(ticker, quote);
        setChanged();
        notifyObservers(new StockEvent(ticker, quote));
    }

    public Float getQuote(String ticker) {
        return tickerToQuote.get(ticker);
    }

  
}
