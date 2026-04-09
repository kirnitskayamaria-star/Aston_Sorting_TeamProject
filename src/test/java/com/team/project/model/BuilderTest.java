package com.team.project.model;

public class BuilderTest {
    Car car = Car.builder().model("Toyota")
            .power(400).year(1997).build();

    Car car2 = Car.builder().model("Nissan")
            .power(600).year(2000).build();

    public void print() {
        System.out.println(car.toString());
        System.out.println(car2.toString());

        System.out.println(car.getYear() + " " + car.getPower() + " " + car.getModel());
        System.out.println(car2.getYear() + " " + car2.getPower() + " " + car2.getModel());
    }
}
