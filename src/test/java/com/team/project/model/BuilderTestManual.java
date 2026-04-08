package com.team.project.model;

import org.junit.jupiter.api.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BuilderTestManual extends BuilderTestAbstract {
    private static String model;
    private static int power;
    private static int year;
    Scanner sc = new Scanner(System.in);

    public void input() throws InputMismatchException {
        System.out.println("Please enter car model: ");
        model = sc.next();
        System.out.println("Please enter engine power: ");
        power = sc.nextInt();
        System.out.println("Please enter production date: ");
        year = sc.nextInt();
    }

    @Test
    @Order(1)
    @DisplayName("Is input data correct?")
    void testCorrectInput() {
        assertDoesNotThrow(() -> input());
    }

    @Test
    @Order(2)
    @DisplayName("Output Data check for Car using Manual data entry")
    public void output() {
        Car car = Car.builder().model(model).power(power).year(year).build();
        assertTrue(car.getModel().length() > 0 && car.getModel().length() < 20);
        assertTrue(car.getPower() < maxPower && car.getPower() > minPower);
        assertTrue(car.getYear() < maxYear + 1 && car.getYear() > minYear);
    }
}
