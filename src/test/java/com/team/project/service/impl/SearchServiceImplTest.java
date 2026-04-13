package com.team.project.service.impl;

import com.team.project.model.Car;
import com.team.project.service.SearchService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

public class SearchServiceImplTest {

    SearchService searchService = new SearchServiceImpl();

    private List<Car> createTestCars() {
        return List.of(
                new Car.CarBuilder().model("BMW").power(3000).year(2010).build(),
                new Car.CarBuilder().model("BMW").power(3500).year(2020).build(),
                new Car.CarBuilder().model("Volvo").power(2500).year(2010).build()
        );
    }

    @Test
    void shouldReturnCorrectCount_IgnoringCase() {

        List<Car> cars = createTestCars();

        String targetModel = "bmw";

        int countCars = searchService.countCars(cars, targetModel);

        assertEquals(2, countCars);

    }

    @Test
    void shouldReturnZero_WhenNoOccurrencesWereFound() {
        List<Car> cars = createTestCars();

        String targetModel = "Toyota";

        int countCars = searchService.countCars(cars, targetModel);

        assertEquals(0, countCars);
    }

    @Test
    public void shouldReturnZero_WhenListIsEmpty() {
        List<Car> cars = Collections.emptyList();
        String targetModel = "Toyota";

        int count = searchService.countCars(cars, targetModel);

        assertEquals(0, count);
    }

    @Test
    public void shouldThrowException_WhenListIsNotInitialized() {
        assertThrows(RuntimeException.class,
                () -> searchService.countCars(null, "bmw"));
    }
}
