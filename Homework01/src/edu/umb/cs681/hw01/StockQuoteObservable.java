package edu.umb.cs681.hw01;

import java.util.HashMap;
import edu.umb.cs680.hw11.MyObservable;
import edu.umb.cs680.hw11.StockEvent;

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
