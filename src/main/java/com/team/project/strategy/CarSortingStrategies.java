package com.team.project.strategy;
import com.team.project.model.Car;
import com.team.project.strategy.SortingStrategy;

import java.util.Comparator;
import java.util.List;

public class CarSortingStrategies {

    public static final Comparator<Car> BY_MODEL = Comparator.comparing(Car::getModel);
    public static final Comparator<Car> BY_POWER = Comparator.comparingInt(Car::getPower);
    public static final Comparator<Car> BY_YEAR = Comparator.comparingInt(Car::getYear);

    public static class SortByModel implements SortingStrategy {
        @Override
        public void sort(List<Car> cars) {
            bubbleSort(cars, BY_MODEL);
        }
    }

    public static class SortByPower implements SortingStrategy {
        @Override
        public void sort(List<Car> cars) {
            bubbleSort(cars, BY_POWER);
        }
    }

    public static class SortByYear implements SortingStrategy {
        @Override
        public void sort(List<Car> cars) {
            bubbleSort(cars, BY_YEAR);
        }
    }

    private static void bubbleSort(List<Car> list, Comparator<Car> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                // Сравниваем j и j + 1
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    // МЕНЯЕМ МЕСТАМИ j и j + 1
                    Car temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

}