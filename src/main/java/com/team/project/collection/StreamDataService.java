package com.team.project.collection;

import java.util.ArrayList;
import com.team.project.model.Car;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDataService {

    public List<Car> fillWithStreams(int count) {
        return Stream.generate(() -> new Car.CarBuilder()
                        .model(generateRandomModel())
                        .power((int) (Math.random() * 300) + 50)
                        .year((int) (Math.random() * 30) + 1996)
                        .build())
                .limit(count)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private String generateRandomModel() {
        String[] models = {"Lada", "Toyota", "BMW", "Audi", "Peugeot", "Lexus", "Mercedes", "Ford"};
        return models[(int) (Math.random() * models.length)];
    }

}
