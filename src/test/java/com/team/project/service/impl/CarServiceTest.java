package com.team.project.service.impl;

import com.team.project.io.DataHandler;
import com.team.project.model.Car;
import com.team.project.io.FillStrategy;
import com.team.project.service.SearchService;
import com.team.project.strategy.SortContext;
import com.team.project.strategy.SortingStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private SearchService searchService;

    @Mock
    private DataHandler dataHandler;

    @Mock
    private SortContext sortContext;

    @InjectMocks
    private CarService carService;

    @Test
    void shouldUpdateCarsList_WhenFillIsCalled() {
        FillStrategy strategy = mock(FillStrategy.class);

        List<Car> mockCars = List.of(mock(Car.class), mock(Car.class));

        when(strategy.fill(2)).thenReturn(mockCars);

        carService.fill(strategy, 2);

        verify(strategy).fill(2);
    }

    @Test
    void shouldSetStrategyAndSort_WhenSortIsCalled() {
        FillStrategy fillStrategy = mock(FillStrategy.class);
        when(fillStrategy.fill(anyInt())).thenReturn(List.of(mock(Car.class)));
        carService.fill(fillStrategy, 1);

        SortingStrategy sortingStrategy = mock(SortingStrategy.class);

        carService.sort(sortingStrategy);

        verify(sortContext).setStrategy(sortingStrategy);
    }

    @Test
    void shouldThrowException_WhenSortingEmptyList() {
        SortingStrategy strategy = mock(SortingStrategy.class);
        assertThrows(IllegalStateException.class, () -> carService.sort(strategy));
    }

    @Test
    void shouldDelegateToDataHandler_WhenSaving() {
        FillStrategy fillStrategy = mock(FillStrategy.class);
        List<Car> mockCars = List.of(mock(Car.class));
        when(fillStrategy.fill(anyInt())).thenReturn(mockCars);
        carService.fill(fillStrategy, 1);

        String path = "cars.txt";
        carService.save(path);

        verify(dataHandler).saveToFile(eq(path), anyList());

    }

    @Test
    void shouldReturnCount_WhenCountingModels() {
        List<Car> cars = List.of(mock(Car.class), mock(Car.class));
        String model = "BMW";

        when(searchService.countCars(anyList(), eq(model))).thenReturn(2);

        int result = carService.countModel(model);

        assertEquals(2, result);

        verify(searchService).countCars(anyList(), eq(model));
    }

    @Test
    void shouldReturnTrue_WhenListIsNotEmpty() {
        FillStrategy strategy = mock(FillStrategy.class);

        List<Car> mockList = List.of(mock(Car.class), mock(Car.class));

        when(strategy.fill(anyInt())).thenReturn(mockList);

        carService.fill(strategy, 1);

        assertTrue(carService.hasCars());

    }

    @Test
    void shouldReturnFalse_WhenListIsEmpty() {
        FillStrategy strategy = mock(FillStrategy.class);
        List<Car> mockList = Collections.emptyList();

        when(strategy.fill(anyInt())).thenReturn(mockList);

        carService.fill(strategy, 0);

        assertFalse(carService.hasCars());
    }
}
