package com.team.project.strategy;

import com.team.project.model.Car;

import java.util.List;

public class SortContext {
    private SortingStrategy strategy;

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(List<Car> cars) {
        if (strategy == null) throw new IllegalStateException("Strategy not set");
        strategy.sort(cars);
    }
}