package com.team.project.collection;

import java.util.List;

import com.team.project.model.Car;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDataService {


    public List<Car> fillWithStreams(int count) {
        return Stream.generate(CarGenerator::generateRandomCar)
                .limit(count)
                .collect(Collectors.toList());
    }

}
