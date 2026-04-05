package com.team.project.strategy;

import com.team.project.model.Car;

import java.util.ArrayList;
import java.util.List;

public class EvenSortStrategy implements SortingStrategy{

    @Override
    public void sort(Car[] cars) {
        if (cars == null || cars.length <= 1) {
            return;
        }

        List<Integer> evenIndices = new ArrayList<>();
        List<Car> evenCars = new ArrayList<>();

        for (int i = 0; i < cars.length; i++) {
            Car car = cars[i];
            if (car.getPower() % 2 == 0) {
                evenIndices.add(i);
                evenCars.add(car);
            }
        }

        manualSortByPower(evenCars);

        for (int j = 0; j < evenIndices.size(); j++) {
            cars[evenIndices.get(j)] = evenCars.get(j);
        }
    }

    private void manualSortByPower(List<Car> cars) {
        for (int i = 1; i < cars.size(); i++) {
            Car key = cars.get(i);
            int j = i - 1;
            while (j >= 0 && cars.get(j).getPower() > key.getPower()) {
                cars.set(j + 1, cars.get(j));
                j--;
            }
            cars.set(j + 1, key);
        }
    }
}
