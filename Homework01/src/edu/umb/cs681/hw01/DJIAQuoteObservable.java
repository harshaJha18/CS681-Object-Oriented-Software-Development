package edu.umb.cs681.hw01;

import edu.umb.cs680.hw11.MyObservable;
import edu.umb.cs680.hw11.DJIAEvent;

public class DJIAQuoteObservable extends MyObservable {

    private float quote;

    public float getQuote() {
        return quote;
    }

    public void setQuote(float newQuote) {
        quote = newQuote;
        setChanged();
        notifyObservers(new DJIAEvent(quote));
        
    }
}


