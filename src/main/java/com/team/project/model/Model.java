package com.team.project.model;

public class Model {
    Car car = Car.builder().model("Toyota")
            .power(400).productionDate(1997).build();

    Car car2 = Car.builder().model("Nissan")
            .power(600).productionDate(2000).build();

    public void print() {
        System.out.println(car.toString());
        System.out.println(car2.toString());

        System.out.println(car.getProductionDate() + " " + car.getPower() + " " + car.getModel());
        System.out.println(car2.getProductionDate() + " " + car2.getPower() + " " + car2.getModel());
    }
}
