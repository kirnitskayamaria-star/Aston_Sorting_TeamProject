package com.team.project.service;

import com.team.project.Car;

import java.util.List;

public interface SearchService {

    Integer countCars(List<Car> cars, Car car);
}
