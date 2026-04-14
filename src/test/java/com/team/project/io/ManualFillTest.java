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
    @DisplayName("Positive check: process one car via console input")
    void positiveManualInput() {
        String input = "Lada\n90\n1980\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ManualFill manualFill = new ManualFill(new Scanner(System.in));
        List<Car> result = manualFill.fill(1);
        assertEquals("Lada", result.get(0).getModel());
    }

    @Test
    @DisplayName("Negative check: ignore invalid data")
    void negativeManualFill() {
        String input = "Lada\nlada\nt\n" +
                "BMW\n200\n1999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ManualFill manualFill = new ManualFill(new Scanner(System.in));
        List<Car> result = manualFill.fill(1);
        assertEquals("BMW", result.get(0).getModel(), "The first car should not be in the list");

    }

}
