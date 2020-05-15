package edu.umb.cs680.hw12;

public class DJIAQuoteObservable extends ObservableEnhanced {

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
