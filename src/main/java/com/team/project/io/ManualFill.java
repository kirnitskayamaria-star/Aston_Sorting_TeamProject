package com.team.project.io;

import java.util.ArrayList;
import java.util.List;
import com.team.project.model.Car;
import com.team.project.validation.CarValidator;


public class ManualFill implements FillStrategy{

    @Override
    public List<Car> fill(int count) {
        return new ArrayList<>();
    }

    public Car processSingleInput(String m, String p, String y) {
        if (!CarValidator.validateAll(m, p, y)) return null;

        return new Car.CarBuilder()
                .model(m)
                .power(Integer.parseInt(p))
                .year(Integer.parseInt(y))
                .build();
    }
}
