package com.team.project.io;

import com.team.project.collection.StreamDataService;
import com.team.project.model.Car;

import java.util.List;

public class RandomFill implements FillStrategy{
    private final StreamDataService streamService = new StreamDataService();

    @Override
    public List<Car> fill(int count) {
        return streamService.fillWithStreams(count);
    }
}
