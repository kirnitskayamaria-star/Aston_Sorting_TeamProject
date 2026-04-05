package com.team.project.strategy;

import com.team.project.model.Car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
        assertDoesNotThrow(() -> strategy.sort(null));
    }

    @Test
    void whenEmptyList_thenNothingChanges() {
        Car[] cars = new Car[0];

        strategy.sort(cars);

        assertEquals(0, cars.length);
    }

    @Test
    void whenCarAlone_thenNothingChanges() {
        Car[] cars = new Car[]{car("Мицубиси", 212, 2007)};

        strategy.sort(cars);

        assertEquals(1, cars.length);
        assertEquals(212, cars[0].getPower());
    }

    @Test
    void whenNoEvenPowerCars_thenNothingChanges() {
        Car[] cars = new Car[]{
                car("A", 1, 2000),
                car("B", 3, 2001),
                car("C", 5, 2002)
        };
        Car[] original = Arrays.copyOf(cars, cars.length);

        strategy.sort(cars);

        assertArrayEquals(original, cars);
    }

    @Test
    void whenSingleEvenPowerCar_thenPositionUnchanged() {
        Car[] cars = new Car[]{
                car("A", 1, 2000),
                car("B", 2, 2001),
                car("C", 3, 2002)
        };
        Car[] original = Arrays.copyOf(cars, cars.length);

        strategy.sort(cars);

        assertArrayEquals(original, cars);
    }

    @Test
    void whenAllPowersEven_thenSortedAscending() {
        Car[] cars = new Car[]{
                car("A", 8, 2000),
                car("B", 2, 2001),
                car("C", 6, 2002),
                car("D", 4, 2003)
        };

        strategy.sort(cars);

        assertEquals(2, cars[0].getPower());
        assertEquals(4, cars[1].getPower());
        assertEquals(6, cars[2].getPower());
        assertEquals(8, cars[3].getPower());
    }

    @Test
    void whenCarBuiltWithoutPower_thenPowerIsZeroAndTreatedAsEven() {
        Car carWithoutPower = Car.builder().model("NoPower").year(2000).build();

        Car[] cars = new Car[]{
                carWithoutPower,
                car("B", 4, 2001),
                car("C", 2, 2002)
        };

        strategy.sort(cars);

        assertEquals(0, cars[0].getPower());
        assertEquals(2, cars[1].getPower());
        assertEquals(4, cars[2].getPower());
    }

    @Test
    void whenMixedOddAndEven_thenEvenSortedAndOddStay() {
        Car[] cars = new Car[]{
                car("A", 5, 2000),
                car("B", 2, 2001),
                car("C", 7, 2002),
                car("D", 8, 2003),
                car("E", 4, 2004),
                car("F", 3, 2005)
        };

        strategy.sort(cars);

        assertEquals(5, cars[0].getPower());
        assertEquals(7, cars[2].getPower());
        assertEquals(3, cars[5].getPower());

        assertEquals(2, cars[1].getPower());
        assertEquals(4, cars[3].getPower());
        assertEquals(8, cars[4].getPower());
    }

    @Test
    void whenEvenPowersWithDuplicates_thenSortedCorrectly() {
        Car[] cars = new Car[]{
                car("A", 4, 2000),
                car("B", 4, 2001),
                car("C", 2, 2002)
        };

        strategy.sort(cars);

        assertEquals(2, cars[0].getPower());
        assertEquals(4, cars[1].getPower());
        assertEquals(4, cars[2].getPower());
    }

    @Test
    void whenAllEvenAlreadySorted_thenOrderUnchanged() {
        Car[] cars = new Car[]{
                car("A", 2, 2000),
                car("B", 4, 2001),
                car("C", 6, 2002)
        };
        Car[] original = Arrays.copyOf(cars, cars.length);

        strategy.sort(cars);

        assertArrayEquals(original, cars);
    }

    @Test
    void whenEvenAlreadySortedAmongOdd_thenPositionsUnchanged() {
        Car[] cars = new Car[]{
                car("A", 5, 2000),
                car("B", 2, 2001),
                car("C", 7, 2002),
                car("D", 4, 2003),
                car("E", 3, 2004)
        };
        Car[] original = Arrays.copyOf(cars, cars.length);

        strategy.sort(cars);

        assertArrayEquals(original, cars);
    }
}
