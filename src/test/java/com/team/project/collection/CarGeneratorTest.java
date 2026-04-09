package com.team.project.collection;

import com.team.project.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class CarGeneratorTest {


    @RepeatedTest(100)
    @DisplayName("Проверка генерации машины")
    void positiveGenerateValidCar() {
        Car car = CarGenerator.generateRandomCar();
        assertTrue(car.getPower() >= 50 && car.getPower() < 350,
                "Мощность вне диапазона [50, 350): " + car.getPower());
        assertTrue(car.getYear() >= 1996 && car.getYear() < 2026,
                "Год вне диапазона [1996, 2026): " + car.getYear());
        assertNotNull(car);
    }

    @RepeatedTest(100)
    @DisplayName("Негативные сценарии проверки генерации машины")
    void negativeGenerateValidCar() {
        Car car = CarGenerator.generateRandomCar();
        assertFalse(car.getPower() < 50 || car.getPower() >= 350,
                "Мощность вне диапазона " + car.getPower());
        assertFalse(car.getYear() < 1996 || car.getYear() >= 2026,
                "Год вне диапазона " + car.getYear());
    }
}
