package com.team.project.collection;

import com.team.project.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class CarGeneratorTest {


    @RepeatedTest(100)
    @DisplayName("Positive CarGenerator check")
    void positiveGenerateValidCar() {
        Car car = CarGenerator.generateRandomCar();
        assertTrue(car.getPower() >= 50 && car.getPower() < 350,
                "Power is out of range [50, 350): " + car.getPower());
        assertTrue(car.getYear() >= 1996 && car.getYear() < 2026,
                "Year is out of range [1996, 2026): " + car.getYear());
        assertNotNull(car);
    }

    @RepeatedTest(100)
    @DisplayName("Negative CarGenerator check")
    void negativeGenerateValidCar() {
        Car car = CarGenerator.generateRandomCar();
        assertFalse(car.getPower() < 50 || car.getPower() >= 350,
                "Power is outside the allowed range " + car.getPower());
        assertFalse(car.getYear() < 1996 || car.getYear() >= 2026,
                "Year is outside the allowed range  " + car.getYear());
    }
}
