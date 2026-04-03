package com.team.project.io;

import com.team.project.model.Car;
import com.team.project.validation.CarValidator;

public class UserInputAdapter {

        public Car adaptInputToCar(String model, String powerStr, String yearStr) {

            if (!CarValidator.validateAll(model, powerStr, yearStr)) {
                return null;
            }

            int power = Integer.parseInt(powerStr);
            int year = Integer.parseInt(yearStr);

            return new Car.CarBuilder()
                    .model(model)
                    .power(power)
                    .year(year)
                    .build();

    }

}
