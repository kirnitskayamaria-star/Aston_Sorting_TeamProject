package com.team.project.io;

import com.team.project.collection.CarGenerator;
import com.team.project.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFillTest {

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Создание и сохранения 100 машин")
    void testDataHandlerFullCycle() {
        DataHandler dataHandler = new DataHandler();
        Path filePath = tempDir.resolve("test_cars.txt");
        String fileName = filePath.toString();
        List<Car> originalCars = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            originalCars.add(CarGenerator.generateRandomCar());
        }

        dataHandler.saveToFile(fileName, originalCars);
        FileFill reader = new FileFill(fileName);
        List<Car> loadedCars = reader.fill(100);
        assertEquals(originalCars.size(), loadedCars.size(), "Количество не совпало!");
        for (int i = 0; i < originalCars.size(); i++) {
            String expected = originalCars.get(i).toString();
            String actual = loadedCars.get(i).toString();
            assertEquals(expected, actual, "Ошибка в данных на позиции " + i);
        }
    }

    @Test
    @DisplayName("Пропуск некорректных строк: чтение только валидных данных")
    void shouldSkipInvalidLines() throws IOException {
        Path filePath = tempDir.resolve("corrupted.txt");
        Files.write(filePath, List.of(
                "ValidModel, 100, 2020",
                "",
                "Broken, data",
                "Car, power, year"
        ));

        FileFill reader = new FileFill(filePath.toString());
        List<Car> result = reader.fill(10);

        assertEquals(1, result.size(), "Должна быть прочитана только 1 валидная машина");
        assertEquals("ValidModel", result.get(0).getModel());
    }


}
