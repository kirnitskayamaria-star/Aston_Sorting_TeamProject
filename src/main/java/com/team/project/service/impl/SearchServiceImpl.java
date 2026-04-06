package com.team.project.service.impl;

import com.team.project.Car;
import com.team.project.service.SearchService;

import java.util.List;

public class SearchServiceImpl implements SearchService {

    @Override
    public Integer countCars(List<Car> cars, String targetModel) {
        if (cars == null || cars.isEmpty() || targetModel == null) {
            return 0;
        }

        return (int) cars.parallelStream()
                .filter(car -> car.getModel().equalsIgnoreCase(targetModel))
                .count();
    }

}
