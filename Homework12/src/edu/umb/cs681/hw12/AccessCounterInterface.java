package edu.umb.cs681.hw12;

import java.nio.file.Path;

public interface AccessCounterInterface {

    public void increment(Path path);
    public Integer getCount(Path path);
    public void printData();

}

