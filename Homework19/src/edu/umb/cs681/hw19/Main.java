package edu.umb.cs681.hw19;


import java.util.stream.Stream;

import java.util.function.BinaryOperator;
import java.util.function.BiFunction;
import java.util.Optional;


public class Main {

    public static Car[] cars = {
            new Car("Mazda", "CX-5", 31, 2000, 25190f),
            new Car("Ram", "1500", 32, 2020, 32147f),
            new Car("Toyota", "Supra", 31, 2018, 49990f),
            new Car("Kia", "Telluride", 26, 2019, 31890f),
            new Car("Volkswagen", "Golf GTI", 32, 2006, 29850f),
            new Car("Honda", "Accord", 38, 2002, 24020f),
            new Car("Porsche", "718 Boxster", 28, 2005, 60950f),
            new Car("Chevrolet", "Corvette", 27, 2018, 59995f),
            new Car("Jeep", "Gladiator", 22, 2015, 33545f),
            new Car("Porsche", "Macan S", 23, 2016, 59400f)
        };

   
    public static Integer getCarModel(Stream<Car> carStream) {
        return carStream.map( (Car c)-> c.getModel() )
        		.reduce(0,(result,carModel)-> ++result,
        		(finalresult,intermediateresult)->
        		finalresult + intermediateresult);
    }
   

    public static void main(String[] args) {

    	System.out.printf("*************CS681 Homework 19*************\n");
        
       int carCount = getCarModel(Stream.of(cars).parallel());
     
        System.out.printf("\nTotal number of Cars using map-reduce stream : "+carCount );
      
        
    }
    
}

