package com.team.project.collection;
import com.team.project.model.Car;
import java.util.Random;

public class CarGenerator {
    private static final String[] MODELS = {"Lada", "Toyota", "BMW", "Audi", "Peugeot", "Lexus", "Mercedes", "Ford"};
    private static final Random RANDOM = new Random();
    public static Car generateRandomCar(){
        return new Car.CarBuilder()
                .model(MODELS[RANDOM.nextInt(MODELS.length)])
                .power(RANDOM.nextInt(300) + 50)
                .year(RANDOM.nextInt(30) + 1996)
                .build();
    }
}
