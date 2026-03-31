public class Model {
    Car car = new Car.CarBuilder("Toyota")
        .power(300).productionDate(1997).build();
    Car car2 = new Car.CarBuilder("BMW")
        .power(200).productionDate(2000).build();

    public void print(){
        System.out.println(car.toString());
        System.out.println(car2.toString());
    }
}
