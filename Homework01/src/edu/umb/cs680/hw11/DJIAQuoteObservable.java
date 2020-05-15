package edu.umb.cs680.hw11;

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
