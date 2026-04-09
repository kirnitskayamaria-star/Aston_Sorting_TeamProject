package com.team.project.collection;

import com.team.project.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StreamDataServiceTest {

    @Test
    @DisplayName("Генерация списка машин: проверка количества и границ значений")
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

    @Test
    @DisplayName("Обработка некорректного количества: возврат пустого списка")
    void negativeStreamGenerator() {
        StreamDataService service = new StreamDataService();

        List<Car> negativeCars = service.fillWithStreams(-1);

        assertNotNull(negativeCars, "Метод не должен возвращать null даже при ошибке");
        assertTrue(negativeCars.isEmpty(), "Список должен быть пустым при отрицательном count");

        List<Car> zeroCars = service.fillWithStreams(0);
        assertTrue(zeroCars.isEmpty(), "Список должен быть пустым при count = 0");
    }
}
