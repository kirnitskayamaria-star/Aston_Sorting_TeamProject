package com.team.project.model;

import java.util.concurrent.ThreadLocalRandom;

public class BuilderTestRandom extends BuilderTestAbstract {
    private int power;
    private int year;
    private String model;

    public void input() {
        power = ThreadLocalRandom.current().nextInt(minPower, maxPower);
        year = ThreadLocalRandom.current().nextInt(minYear, maxYear + 1);
        int randomModelNumber = (int) (Math.random() * models.length);
        model = models[randomModelNumber];
    }

    public Car output() {
        Car car = Car.builder().model(this.model).power(this.power).year(this.year).build();
        return car;
    }
}
