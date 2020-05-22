package edu.umb.cs680.hw11;

public class StockEvent {

    private String ticker;
    private Float quote;

    public StockEvent(String ticker, Float quote) {
        this.ticker = ticker;
        this.quote = quote;
    }

    public String getTicker() {
        return ticker;
    }

    public Float getQuote() {
        return quote;
    }

}
