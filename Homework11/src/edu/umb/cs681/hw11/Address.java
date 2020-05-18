package edu.umb.cs681.hw11;

import java.util.Formatter;


public final class Address {

    private final String street, city, state;
    private final String zipcode; // since zipcode can begin with "0", don't represent as int

    public Address(String street, String city, String state, String zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public boolean equals(Address other) {
        return this.street == other.street && this.city == other.city && this.state == other.state && this.zipcode == other.zipcode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("{street: %s, city: %s, state: %s, zipcode: %s}",
                                this.street, this.city, this.state, this.zipcode);
        return sb.toString();
    }

    public Address change(String street, String city,
                          String state, String zipcode) {
        return new Address(street, city, state, zipcode);
        
    }

}

