//package com.team.project.model;

public class Car {
    private final String model;
    private final int power;
    private final int year;

    private Car(CarBuilder builder) {
        this.model = builder.model;
        this.power = builder.power;
        this.year = builder.year;
    }

    public static CarBuilder builder() {
        return new CarBuilder();
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        String sb = this.model + " " +
                this.power + " hp, " +
                this.year + " y.";
        return sb;
    }

    public static class CarBuilder {
        private String model;
        private int power;
        private int year;

        public CarBuilder model(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder power(int power) {
            this.power = power;
            return this;
        }

        public CarBuilder year(int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}