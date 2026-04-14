package com.team.project.service.impl;

import com.team.project.model.Car;
import com.team.project.service.SearchService;

import java.util.List;
import java.util.Objects;

public class SearchServiceImpl implements SearchService {

    @Override
    public Integer countCars(List<Car> cars, String targetModel) {

        Objects.requireNonNull(cars, "List of cars must not be null");

        if (cars.isEmpty() || targetModel == null) {
            return 0;
        }

        return (int) cars.parallelStream()
                .filter(car -> car.getModel().equalsIgnoreCase(targetModel))
                .count();
    }

}
