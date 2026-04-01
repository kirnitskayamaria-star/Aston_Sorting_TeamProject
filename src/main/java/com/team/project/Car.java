package com.team.project;
/** */
public class Car{
    private String model;
    private int power;
    private int productionDate;

    private Car(CarBuilder builder){
        this.model = builder.model;
        this.power = builder.power;
        this.productionDate = builder.productionDate;
    }

    public static class CarBuilder{
        private final String model;
        private int power;
        private int productionDate; 

        public CarBuilder(String model){
            this.model = model;
        }

        public CarBuilder power(int power){
            this.power = power;
            return this;
        }

        public CarBuilder productionDate(int productionDate){
            this.productionDate = productionDate;
            return this;
        }

        public Car build(){
            return new Car(this);
        }
    }

    public String getModel(){
        return model;
    }
    public int getPower(){
        return power;
    }
    public int getProductionDate(){
        return productionDate;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.model + " ");
        sb.append(this.power + " hp, ");
        sb.append(this.productionDate + " y.");
        return sb.toString();
    }
}
