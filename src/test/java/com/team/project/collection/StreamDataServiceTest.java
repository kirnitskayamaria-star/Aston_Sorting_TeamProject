package com.team.project.collection;

import com.team.project.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StreamDataServiceTest {

    @Test
    @DisplayName("Positive check: generation of car list")
    void positiveStreamGenerator() {
        StreamDataService service = new StreamDataService();
        int count = 100;
        List<Car> cars = service.fillWithStreams(count);
        assertNotNull(cars, "The list should not be null");
        assertEquals(count, cars.size(), "The list size is incorrect " + cars.size());

        Optional<Car> invalidCar = cars.stream().filter(car -> car == null ||
                        car.getPower() < 50 || car.getPower() >= 350 ||
                        car.getYear() < 1996 || car.getYear() >= 2026)
                .findFirst();

        assertTrue(invalidCar.isEmpty(), "An invalid car is in the list " + invalidCar.orElse(null) );
    }

    @Test
    @DisplayName("Negative check: return an empty list")
    void negativeStreamGenerator() {
        StreamDataService service = new StreamDataService();

        List<Car> negativeCars = service.fillWithStreams(-1);

        assertNotNull(negativeCars, "The method should not return null");
        assertTrue(negativeCars.isEmpty(), "The list should be empty for a negative count");

        List<Car> zeroCars = service.fillWithStreams(0);
        assertTrue(zeroCars.isEmpty(), "The list should be empty for 0");
    }
}
