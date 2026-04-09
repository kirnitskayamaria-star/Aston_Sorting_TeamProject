package com.team.project.service.impl;

import com.team.project.Car;
import com.team.project.service.SearchService;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private List<Car> cars = new ArrayList<>();
    private final SearchService searchService;
    private final DataHandler dataHandler;
    private final SortContext sortContext;

    public CarService(SearchService searchService, DataHandler dataHandler, SortContext sortContext) {
        this.searchService = searchService;
        this.dataHandler = dataHandler;
        this.sortContext = sortContext;
    }

    public void fill(FillStrategy strategy, int count) {
        this.cars = strategy.fill(count);
    }

    public void sort(SortingStrategy strategy) {
        if (cars == null || cars.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        sortContext.setStrategy(strategy);
        sortContext.sort(cars);
    }

    public void save(String fileName) {
        if (cars == null || cars.isEmpty()) {
            System.out.println("Car list is empty");
            return;
        }
        dataHandler.saveToFile(fileName, cars);
    }

    public int countModel(String model) {
        return searchService.countCars(cars, model);
    }

    public boolean hasCars() {
        return cars != null && !cars.isEmpty();
    }
}
