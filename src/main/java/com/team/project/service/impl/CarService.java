package com.team.project.service.impl;

import com.team.project.io.DataHandler;
import com.team.project.model.Car;
import com.team.project.io.FillStrategy;
import com.team.project.service.SearchService;
import com.team.project.strategy.SortContext;
import com.team.project.strategy.SortingStrategy;

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
            throw new IllegalStateException("Cannot sort: list is empty");
        }
        sortContext.setStrategy(strategy);
        sortContext.sort(cars);
    }

    public void save(String fileName) {
        if (cars == null || cars.isEmpty()) {
            throw new IllegalStateException("Cannot save: list is empty");
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
