import java.util.InputMismatchException;
import java.util.Scanner;

public class BuilderTestManual extends BuilderTestAbstract {
    Scanner sc = new Scanner(System.in);

    public void input() throws InputMismatchException {
        while (true) {
            System.out.println("Please enter car model: ");
            model = sc.next();
            if (!model.chars().anyMatch(Character::isLetterOrDigit)) {
                model = "";
                power = 0;
                year = 0;
                continue;
            }
            System.out.println("Please enter engine power: ");
            power = sc.nextInt();
            if (power < minPower || power > maxPower) {
                model = "";
                power = 0;
                year = 0;
                continue;
            }
            System.out.println("Please enter production date: ");
            year = sc.nextInt();
            if (year < minYear || year > maxYear) {
                model = "";
                power = 0;
                year = 0;
                continue;
            }
            if (model.length() != 0 && power != 0 && year != 0) {
                System.out.println("Data received!");
                break;
            }
        }
    }

    public Car output() {
        Car car = Car.builder().model(model).power(power).year(year).build();
        return car;
    }
}
