package com.team.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuilderTestRandom extends BuilderTestAbstract {
    private int power;
    private int year;
    private String model;

    @Test
    @DisplayName("Input Data check")
    public void input() {
        power = ThreadLocalRandom.current().nextInt(minPower, maxPower);
        assertTrue(power > minPower && power < maxPower);
        year = ThreadLocalRandom.current().nextInt(minYear, maxYear + 1);
        assertTrue(year > minYear && year < maxYear + 1);
        int randomModelNumber = (int) (Math.random() * models.length);
        assertTrue(randomModelNumber >= 0 && randomModelNumber < models.length);
        model = models[randomModelNumber];
    }

    @Test
    @DisplayName("Output Data check for Car object")
    public void output() {
        input();
        Car car = Car.builder().model(this.model).power(this.power).year(this.year).build();
        assertTrue(car.getModel().length() > 0 && car.getModel().length() < 20);
        assertTrue(car.getPower() < maxPower && car.getPower() > minPower);
        assertTrue(car.getYear() < maxYear + 1 && car.getYear() > minYear);
    }
}
