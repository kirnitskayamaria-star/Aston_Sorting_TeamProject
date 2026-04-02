package com.team.project.service.impl;

import com.team.project.Car;
import com.team.project.service.SearchService;

import java.util.List;

public class SearchServiceImpl implements SearchService {

    @Override
    public Integer countCars(List<Car> cars, Car targetCar) {
        if (cars == null || cars.isEmpty() || targetCar == null) {
            return 0;
        }

        return (int) cars.parallelStream()
                .filter(car -> car.equals(targetCar))
                .count();
    }

}
