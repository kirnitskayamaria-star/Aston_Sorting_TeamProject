package com.team.project.collection;

import com.team.project.model.Car;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StreamDataServiceTest {
    @Test
    void positiveStreamGenerator() {
        StreamDataService service = new StreamDataService();
        int count = 100;
        List<Car> cars = service.fillWithStreams(count);
        assertNotNull(cars, "Список пуст");
        assertEquals(count, cars.size(), "Размер списка неверный " + cars.size());

        Optional<Car> invalidCar = cars.stream().filter(car -> car == null ||
                        car.getPower() < 50 || car.getPower() >= 350 ||
                        car.getYear() < 1996 || car.getYear() >= 2026)
                .findFirst();

        assertTrue(invalidCar.isEmpty(), "В списке обнаружена некорректная машина!" + invalidCar.orElse(null) );
    }
}
