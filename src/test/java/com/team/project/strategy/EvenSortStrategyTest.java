package com.team.project.strategy;

import com.team.project.model.Car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvenSortStrategyTest {

    private EvenSortStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new EvenSortStrategy();
    }

    private Car car(String model, int power, int year) {
        return new Car.CarBuilder().model(model).power(power).year(year).build();
    }

    @Test
    void whenNullInput_thenNoException() {
        List<Car> cars = null;

        assertDoesNotThrow(() -> strategy.sort(cars));
    }

    @Test
    void whenEmptyList_thenNothingChanges() {
        List<Car> cars = new ArrayList<>();

        strategy.sort(cars);

        assertTrue(cars.isEmpty());
    }

    @Test
    void whenCarAlone_thenNothingChanges() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("Мицубиси", 212, 2007));

        strategy.sort(cars);

        assertEquals(1, cars.size());
    }

    @Test
    void whenNoEvenPowerCars_thenNothingChanges() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("A", 1, 2000));
        cars.add(car("B", 3, 2001));
        cars.add(car("C", 5, 2002));
        List<Car> original = new ArrayList<>(cars);

        strategy.sort(cars);

        assertEquals(original.size(), cars.size());
        for (int i = 0; i < cars.size(); i++) {
            assertEquals(original.get(i).getPower(), cars.get(i).getPower());
            assertEquals(original.get(i).getModel(), cars.get(i).getModel());
        }
    }

    @Test
    void whenSingleEvenPowerCar_thenPositionUnchanged() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("A", 1, 2000));
        cars.add(car("B", 2, 2001));
        cars.add(car("C", 3, 2002));
        List<Car> original = new ArrayList<>(cars);

        strategy.sort(cars);

        for (int i = 0; i < cars.size(); i++) {
            assertEquals(original.get(i).getPower(), cars.get(i).getPower());
        }
    }

    @Test
    void whenAllPowersEven_thenSortedAscending() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("A", 8, 2000));
        cars.add(car("B", 2, 2001));
        cars.add(car("C", 6, 2002));
        cars.add(car("D", 4, 2003));

        strategy.sort(cars);

        assertEquals(2, cars.get(0).getPower());
        assertEquals(4, cars.get(1).getPower());
        assertEquals(6, cars.get(2).getPower());
        assertEquals(8, cars.get(3).getPower());
    }

    @Test
    void whenCarBuiltWithoutPower_thenPowerIsZeroAndTreatedAsEven() {
        Car carWithoutPower = Car.builder().model("NoPower").year(2000).build();

        List<Car> cars = new ArrayList<>();
        cars.add(carWithoutPower);
        cars.add(car("B", 4, 2001));
        cars.add(car("C", 2, 2002));

        strategy.sort(cars);

        assertEquals(0, cars.get(0).getPower());
        assertEquals(2, cars.get(1).getPower());
        assertEquals(4, cars.get(2).getPower());
    }

    @Test
    void whenMixedOddAndEven_thenEvenSortedAndOddStay() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("A", 5, 2000));
        cars.add(car("B", 2, 2001));
        cars.add(car("C", 7, 2002));
        cars.add(car("D", 8, 2003));
        cars.add(car("E", 4, 2004));
        cars.add(car("F", 3, 2005));

        strategy.sort(cars);

        assertEquals(5, cars.get(0).getPower());
        assertEquals(7, cars.get(2).getPower());
        assertEquals(3, cars.get(5).getPower());

        assertEquals(2, cars.get(1).getPower());
        assertEquals(4, cars.get(3).getPower());
        assertEquals(8, cars.get(4).getPower());
    }

    @Test
    void whenEvenPowersWithDuplicates_thenSortedCorrectly() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("A", 4, 2000));
        cars.add(car("B", 4, 2001));
        cars.add(car("C", 2, 2002));

        strategy.sort(cars);

        assertEquals(2, cars.get(0).getPower());
        assertEquals(4, cars.get(1).getPower());
        assertEquals(4, cars.get(2).getPower());
    }

    @Test
    void whenAllEvenAlreadySorted_thenOrderUnchanged() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("A", 2, 2000));
        cars.add(car("B", 4, 2001));
        cars.add(car("C", 6, 2002));
        List<Car> original = new ArrayList<>(cars);

        strategy.sort(cars);

        for (int i = 0; i < cars.size(); i++) {
            assertEquals(original.get(i).getPower(), cars.get(i).getPower());
        }
    }

    @Test
    void whenEvenAlreadySortedAmongOdd_thenPositionsUnchanged() {
        List<Car> cars = new ArrayList<>();
        cars.add(car("A", 5, 2000));
        cars.add(car("B", 2, 2001));
        cars.add(car("C", 7, 2002));
        cars.add(car("D", 4, 2003));
        cars.add(car("E", 3, 2004));
        List<Car> original = new ArrayList<>(cars);

        strategy.sort(cars);

        for (int i = 0; i < cars.size(); i++) {
            assertEquals(original.get(i).getPower(), cars.get(i).getPower());
        }
    }
}
