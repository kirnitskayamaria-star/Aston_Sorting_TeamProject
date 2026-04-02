package com.team.project.collection;
import com.team.project.model.Car;
import java.util.Random;

public class CarGenerator {
    private static final String[] MODELS = {"Lada", "Toyota", "BMW", "Audi", "Peugeot", "Lexus", "Mercedes", "Ford"};
    private static final Random RANDOM = new Random();

    private static final int MAX_ADDITIONAL_POWER = 300;
    private static final int MIN_POWER = 50;
    private static final int MAX_YEAR_RANGE = 30;
    private static final int MIN_YEAR = 1996;

    public static Car generateRandomCar(){
        return new Car.CarBuilder()
                .model(MODELS[RANDOM.nextInt(MODELS.length)])
                .power(RANDOM.nextInt(MAX_ADDITIONAL_POWER) + MIN_POWER)
                .year(RANDOM.nextInt(MAX_YEAR_RANGE) + MIN_YEAR)
                .build();
    }
}
