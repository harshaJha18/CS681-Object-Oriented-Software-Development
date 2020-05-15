package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {

    private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<ConcurrentSingleton>(null);
    private static int numConstructorCalls = 0;
    
    private ConcurrentSingleton() {
        numConstructorCalls += 1;
    }

    public static ConcurrentSingleton getInstance() {
    	ConcurrentSingleton cs = instance.get();

    	if (cs == null) {
    		cs = new ConcurrentSingleton();
    		if (!instance.compareAndSet(null, cs))
    		cs = instance.get();
    		}
    		return cs;

       
    }

    public static void printNumConstructorCalls() {
        System.out.printf("\nAtomicReference Concurrent Singleton: number of calls to my constructor is %d\n",
                          numConstructorCalls);
    }
}
