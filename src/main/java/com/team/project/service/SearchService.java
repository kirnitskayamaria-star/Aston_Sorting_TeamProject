package com.team.project.service;

import com.team.project.model.Car;

import java.util.List;

public interface SearchService {

    Integer countCars(List<Car> cars, String targetModel);
}
