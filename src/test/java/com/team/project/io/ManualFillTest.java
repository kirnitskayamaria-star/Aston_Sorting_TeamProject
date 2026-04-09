package com.team.project.io;

import com.team.project.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManualFillTest {
    @Test
    @DisplayName("Успешная обработка одной машины через консольный ввод")
    void positiveManualInput() {
        String input = "Lada\n90\n1980\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ManualFill manualFill = new ManualFill(new Scanner(System.in));
        List<Car> result = manualFill.fill(1);
        assertEquals("Lada", result.get(0).getModel());
    }

    @Test
    @DisplayName("Игнорирование неверных данных и успешный повторный ввод")
    void negativeManualFill() {
        String input = "Lada\nlada\nt\n" +
                "BMW\n200\n1999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ManualFill manualFill = new ManualFill(new Scanner(System.in));
        List<Car> result = manualFill.fill(1);
        assertEquals("BMW", result.get(0).getModel(), "Первая машина не должна была попасть в список");

    }

}
