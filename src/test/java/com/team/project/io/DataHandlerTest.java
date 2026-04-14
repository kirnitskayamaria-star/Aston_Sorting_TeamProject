package com.team.project.io;

import com.team.project.model.Car;
import com.team.project.collection.CarGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Positive check: save Car object to a text file")
    void positiveSaveCarsToFile() throws IOException {
        DataHandler dataHandler = new DataHandler();
        Path filePath = tempDir.resolve("test_cars.txt");
        Car car = CarGenerator.generateRandomCar();
        List<Car> testCars = List.of(car);
        String expectedString = car.getModel() + "," + car.getPower() + "," + car.getYear();
        dataHandler.saveToFile(filePath.toString(), testCars);
        assertTrue(Files.exists(filePath), "The file should exist");
        List<String> lines = Files.readAllLines(filePath);
        assertEquals(1, lines.size(), "The file should contain one line");
        assertEquals(expectedString, lines.get(0), "File content should match the format: model, power, year");
    }

}
