package com.team.project.controller;

import com.team.project.io.FileFill;
import com.team.project.io.ManualFill;
import com.team.project.io.RandomFill;
import com.team.project.service.impl.CarService;
import com.team.project.strategy.CarSortingStrategies;
import com.team.project.strategy.EvenSortStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest {

    @Mock
    private CarService carService;

    private MenuController createController(String input) {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(input.getBytes());
        return new MenuController(new Scanner(byteStream), carService);
    }

    @Test
    void shouldExit() {
        createController("0\n").start();
        verifyNoInteractions(carService);
    }

    @Test
    void shouldFillFromFile() {
        createController("1\n1\n10\ntest.txt\n0\n").start();
        verify(carService).fill(any(FileFill.class), eq(10));
    }

    @Test
    void shouldFillRandomly() {
        createController("1\n2\n5\n0\n").start();
        verify(carService).fill(any(RandomFill.class), eq(5));
    }

    @Test
    void shouldFillManually() {
        createController("1\n3\n3\n0\n").start();
        verify(carService).fill(any(ManualFill.class), eq(3));
    }


    @Test
    void shouldPrintList() {
        createController("2\n0\n").start();
        verify(carService).printList();
    }

    @Test
    void shouldSaveToFile() {
        createController("3\nout.txt\n0\n").start();
        verify(carService).save("out.txt");
    }

    @Test
    void shouldSearchModel() {
        createController("5\nBMW\n0\n").start();
        verify(carService).countModel("BMW");
    }


    @Test
    void shouldSortByModel() {
        createController("4\n1\n0\n").start();
        verify(carService).sort(any(CarSortingStrategies.SortByModel.class));
    }

    @Test
    void shouldSortByPower() {
        createController("4\n2\n0\n").start();
        verify(carService).sort(any(CarSortingStrategies.SortByPower.class));
    }

    @Test
    void shouldSortByYear() {
        createController("4\n3\n0\n").start();
        verify(carService).sort(any(CarSortingStrategies.SortByYear.class));
    }

    @Test
    void shouldSortByEvenStrategy() {
        createController("4\n4\n0\n").start();
        verify(carService).sort(any(EvenSortStrategy.class));
    }


    @Test
    @DisplayName("Валидация: Повтор ввода при невалидном числе")
    void shouldRetryOnInvalidInt() {
        createController("abc\n2\n0\n").start();
        verify(carService).printList();
    }

    @Test
    void shouldRetryOnNegativeCount() {
        createController("1\n2\n-5\n3\n0\n").start();
        verify(carService).fill(any(RandomFill.class), eq(3));
    }

    @Test
    void shouldHandleServiceException() {
        doThrow(new RuntimeException("DB Error")).when(carService).printList();

        createController("2\n0\n").start();

        verify(carService).printList();
    }
}