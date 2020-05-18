package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class Customer {
    private Address address;
    private ReentrantLock lock;

    public Customer(Address addr) {
        this.address = addr;
        this.lock = new ReentrantLock();
    }
    
    public void setAddress(Address addr) {
        lock.lock();
        try {
            System.out.printf("\tCustomer.setAddress (thread %d): %s street -> %s street\n",
                              Thread.currentThread().getId(),
                              address.getStreet(),
                              addr.getStreet());
            address = addr;
        } finally {
            lock.unlock();
        }        
    }


    public Address getAddress() {
        Address addr;
        lock.lock();
        addr = this.address;
        lock.unlock();
        return addr;
    }
}
