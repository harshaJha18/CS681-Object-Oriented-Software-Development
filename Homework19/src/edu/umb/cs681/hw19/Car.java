package edu.umb.cs681.hw19;



public class Car {

    private String make, model;
    private int mileage, year;
    private float price;

    public Car(String make, String model, int mileage, int year, float price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public int getMileage() {
        return this.mileage;
    }

    public int getYear() {
        return this.year;
    }

    public float getPrice() {
        return this.price;
    }

    public String toString() {
        return String.format("%s %s %d", make, model, year);
    }

   

}
