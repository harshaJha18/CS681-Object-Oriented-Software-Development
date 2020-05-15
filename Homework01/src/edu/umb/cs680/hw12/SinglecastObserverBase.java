package edu.umb.cs680.hw12;

abstract class SinglecastObserverBase extends ObserverBase implements ObserverEnhanced {

    public void update(Object event) {
        if (event instanceof StockEvent) {
            addReceivedStockEvent((StockEvent) event);
        } else if (event instanceof DJIAEvent) {
            addReceivedDJIAEvent((DJIAEvent) event);
        }
    }

}
