package com.team.project.model;

public class Car {
    private final String model;
    private final int power;
    private final int productionDate;

    private Car(CarBuilder builder) {
        this.model = builder.model;
        this.power = builder.power;
        this.productionDate = builder.productionDate;
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

    public int getProductionDate() {
        return productionDate;
    }

    public String toString() {
        String sb = this.model + " " +
                this.power + " hp, " +
                this.productionDate + " y.";
        return sb;
    }

    public static class CarBuilder {
        private String model;
        private int power;
        private int productionDate;

        public CarBuilder model(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder power(int power) {
            this.power = power;
            return this;
        }

        public CarBuilder productionDate(int productionDate) {
            this.productionDate = productionDate;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}