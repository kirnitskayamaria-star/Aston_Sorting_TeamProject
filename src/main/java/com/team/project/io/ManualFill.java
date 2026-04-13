package com.team.project.io;

import com.team.project.model.Car;
import com.team.project.validation.CarValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualFill implements FillStrategy {
    private final Scanner scanner;

    public ManualFill(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List<Car> fill(int count) {
        List<Car> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Car #" + (i + 1) + " ---");
            System.out.print("Model: ");
            String m = scanner.nextLine();
            System.out.print("Power: ");
            String p = scanner.nextLine();
            System.out.print("Year: ");
            String y = scanner.nextLine();

            Car car = processSingleInput(m, p, y);

            if (car != null) {
                list.add(car);
            } else {
                System.out.println("Invalid data. Please try again.");
                i--;
            }
        }
        return list;
    }

    private Car processSingleInput(String m, String p, String y) {
        if (!CarValidator.validateAll(m, p, y)) {
            return null;
        }
        try {
            return new Car.CarBuilder()
                    .model(m)
                    .power(Integer.parseInt(p))
                    .year(Integer.parseInt(y))
                    .build();
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
