package com.team.project.model;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BuilderTestFile extends BuilderTestAbstract {

    private static List<String> lines;
    private static List<String> singleLineBrokenUp;
    private static int power = 0;
    private static int year = 0;
    private static String model;
    private final String name = "data.txt";

    public void getFileData() throws IOException {
        String dataPath = System.getProperty("user.dir") + "/src/test/java/" + name;
        System.out.println(dataPath);
        lines = Files.readAllLines(Paths.get(dataPath), StandardCharsets.UTF_8);
    }

    @Test
    @Order(1)
    @DisplayName("Data Input Check")
    void correctInputData() {
        assertDoesNotThrow(() -> getFileData());
        decomposition();
    }

    public void decomposition() {
        for (String line : lines) {
            singleLineBrokenUp = Arrays.asList(line.split(" "));
            input();
        }
    }

    public void input() {
        int _power = 0;
        int _year = 0;
        if (singleLineBrokenUp.get(0).chars().anyMatch(Character::isLetterOrDigit))
            model = singleLineBrokenUp.get(0);
        if (singleLineBrokenUp.get(1).chars().anyMatch(Character::isDigit))
            _power = Integer.parseInt(singleLineBrokenUp.get(1));
        if (singleLineBrokenUp.get(2).chars().anyMatch(Character::isDigit))
            _year = Integer.parseInt(singleLineBrokenUp.get(2));

        if (_power > minPower && _power < maxPower)
            power = _power;
        else power = 101;
        if (_year > minYear && _year < maxYear)
            year = _year;
        else year = 1901;
        output();
    }

    @Test
    @DisplayName("Check the data for the car")
    public void output() {
        Car car = Car.builder().model(model).power(power).year(year).build();
        assertTrue(car.getModel().length() > 0 && car.getModel().length() < 20);
        assertTrue(car.getPower() < maxPower && car.getPower() > minPower);
        assertTrue(car.getYear() < maxYear + 1 && car.getYear() > minYear);
    }

}
