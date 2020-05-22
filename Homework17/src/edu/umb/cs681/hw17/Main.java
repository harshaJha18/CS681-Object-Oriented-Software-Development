package edu.umb.cs681.hw17;

import edu.umb.cs681.hw15.ThreadSafeStockQuoteObservable;
import edu.umb.cs680.hw11.ThreeDeeObserver;
import edu.umb.cs680.hw11.StockEvent;

public class Main {
    
    public static void main(String[] args) {
        System.out.printf("*************CS681 Homework 17*************\n");
        ThreadSafeStockQuoteObservable SQObservable =
            new ThreadSafeStockQuoteObservable();
        ThreeDeeObserver threeDeeObserver = new ThreeDeeObserver();
        SQObservable.addObserver(threeDeeObserver);
        SQObservable.changeQuote("ENRN", 99f);
        StockEvent e = threeDeeObserver.getLastReceivedStockEvent();
        System.out.printf("\n3D Observer heard that %s is now %f\n",
                          e.getTicker(),
                          e.getQuote());
    }
}
