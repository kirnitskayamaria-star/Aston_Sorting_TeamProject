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
    @DisplayName("Успешная запись объекта Car в текстовый файл")
    void positiveSaveCarsToFile() throws IOException {
        DataHandler dataHandler = new DataHandler();
        Path filePath = tempDir.resolve("test_cars.txt");
        List<Car> testCars = List.of(CarGenerator.generateRandomCar());
        String expectedString = testCars.get(0).toString();
        dataHandler.saveToFile(filePath.toString(), testCars);
        assertTrue(Files.exists(filePath), "Файл должен существовать");
        List<String> lines = Files.readAllLines(filePath);
        assertEquals(1, lines.size(), "В файле должна быть одна строка");
        assertEquals(expectedString, lines.get(0), "Данные в файле не совпадают с toString машины");
    }

}
