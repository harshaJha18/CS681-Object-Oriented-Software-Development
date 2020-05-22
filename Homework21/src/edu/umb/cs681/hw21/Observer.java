package edu.umb.cs681.hw21;

@FunctionalInterface
interface Observer {
    void update(Observable obs, Object obj);
}

