package edu.umb.cs681.hw02;


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


    private static <T> BinaryOperator<T>
        nullFriendlyComp(BinaryOperator<T> f) {
        return (T m, T n) -> m == null ? n : f.apply(m, n);
    }

    private static Optional<Float> getXmostCarPrice(Stream<Car> carStream, BinaryOperator<Float> moreX) {
        Float val = carStream.map((Car c) -> c.getPrice())
            .reduce(null, (nullFriendlyComp(moreX)));
        return Optional.ofNullable(val);
    }

    public static Integer getCarCount(Stream<Car> carStream) {
        return carStream.map((Car c) -> 1)
            .reduce(0, Integer::sum);
    }
   

    public static void main(String[] args) {

    	 System.out.printf("*************CS681 Homework 02*************\n");
        
        Integer carCount = getCarCount(Stream.of(cars));
     
        Float maxCarPrice = getXmostCarPrice(Stream.of(cars), Float::max).get();
        Float minCarPrice = getXmostCarPrice(Stream.of(cars), Float::min).get();

        System.out.printf("\nThe price range of the %d cars you considered is from %.2f to %.2f.\n",
                          carCount, maxCarPrice, minCarPrice);
      
        
    }
    
}

