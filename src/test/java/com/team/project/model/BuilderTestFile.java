import java.util.Arrays;
import java.util.List;

class FileBuilder extends BuilderTestAbstract {
    List<String> lines;

    public FileBuilder(String line) {
        lines = Arrays.asList(line.split(" "));
        input();
    }

    public void input() {
        int _power = 0;
        int _year = 0;
        if (lines.get(0).chars().anyMatch(Character::isLetterOrDigit))
            model = lines.get(0);
        if (lines.get(1).chars().anyMatch(Character::isDigit))
            _power = Integer.parseInt(lines.get(1));
        if (lines.get(2).chars().anyMatch(Character::isDigit))
            _year = Integer.parseInt(lines.get(2));

        if (_power > minPower && _power < maxPower)
            power = _power;
        else power = 0;
        if (_year > minYear && _year < maxYear)
            year = _year;
        else year = 0;
    }

    public Car output() {
        Car car = Car.builder().model(model).power(power).year(year).build();
        return car;
    }
}

