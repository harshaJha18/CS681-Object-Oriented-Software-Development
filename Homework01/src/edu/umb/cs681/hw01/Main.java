package edu.umb.cs681.hw01;

import edu.umb.cs680.hw11.MyObserver;
import edu.umb.cs680.hw11.DJIAEvent;
import edu.umb.cs680.hw11.DJIAQuoteObserver;
import edu.umb.cs680.hw11.StockEvent;
import edu.umb.cs680.hw11.StockQuoteObserver;

import edu.umb.cs680.hw12.ObservableEnhanced;
import edu.umb.cs680.hw12.ObserverEnhanced;


public class Main {
    public static void main(String[] args) {
    	  System.out.printf("*************CS681 Homework 01*************\n");
        StockQuoteObservable sqObservable = new StockQuoteObservable();
        MyObserver threeDeeStockQuoteRenderer = (Object e) ->
        System.out.printf("\n3D rendering of stock %s at %.2f!\n",
                              ((StockEvent) e).getTicker(),
                              ((StockEvent) e).getQuote());
        sqObservable.addObserver((MyObserver) threeDeeStockQuoteRenderer);
        sqObservable.changeQuote("ENRN", 99f);

        DJIAQuoteObservable djiaObservable = new DJIAQuoteObservable();
        MyObserver threeDeeDJIARenderer = (Object e) -> {
            System.out.printf("3D rendering of DJIA at %.2f!\n",
                              ((DJIAEvent) e).getQuote());
        };
        djiaObservable.addObserver((MyObserver) threeDeeDJIARenderer);
        djiaObservable.setQuote(9999f);

        StockQuoteObservableEnhanced sqObservableE =
            new StockQuoteObservableEnhanced();
        ObserverEnhanced<StockEvent> fourDeeStockQuoteRendererE =
            (StockEvent e) ->
            System.out.printf("4D rendering of stock %s at %.2f!\n",
                              e.getTicker(),
                              e.getQuote());
        sqObservableE.addObserver(fourDeeStockQuoteRendererE);
        sqObservableE.changeQuote("ENRN", 9f);

    }
    
}
