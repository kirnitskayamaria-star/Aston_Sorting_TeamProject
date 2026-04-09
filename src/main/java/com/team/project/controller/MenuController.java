package com.team.project.controller;


import com.team.project.service.impl.CarService;
import com.team.project.strategy.CarSortingStrategies;

import java.util.Scanner;

public class MenuController {
    private final Scanner scanner;
    private final CarService carService;

    public MenuController(Scanner scanner, CarService carService) {
        this.scanner = scanner;
        this.carService = carService;
    }

    public void start() {
        while (true) {
            mainMenu();
            int choice = checkInt();

            switch (choice) {
                case 1 -> fillMenu();
                case 2 -> writeFileMenu();
                case 3 -> sortMenu();
                case 4 -> searchMenu();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Error: invalid choice");
            }
        }
    }

    private void fillMenu() {
        System.out.println("\n1. File | 2. Random | 3. Manual");
        int choice = checkInt();
        System.out.print("Count: ");
        int count = checkCount();

        switch (choice) {
            case 1 -> {
                System.out.print("File name: ");
                carService.fill(new FileFill(scanner.nextLine()), count);
            }
            case 2 -> carService.fill(new RandomFill(), count);
            case 3 -> carService.fill(new ManualFill(scanner), count);
        }
    }

    private void sortMenu() {
        System.out.println("\n1. Model | 2. Power | 3. Year | 4. Custom");
        int choice = checkInt();
        try {
            switch (choice) {
                case 1 -> carService.sort(new CarSortingStrategies.SortByModel());
                case 2 -> carService.sort(new CarSortingStrategies.SortByPower());
                case 3 -> carService.sort(new CarSortingStrategies.SortByYear());
                case 4 -> carService.sort(new EvenSortStrategy());
                default -> System.out.println("Error: invalid choice");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeFileMenu() {
        System.out.print("File name: ");

        try {
            carService.save(scanner.nextLine());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchMenu() {
        System.out.print("Model: ");
        String model = scanner.nextLine();
        try {
            System.out.println("Found: " + carService.countModel(model));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    private void mainMenu() {
        System.out.println("\n1. Fill | 2. Save | 3. Sort | 4. Count | 0. Exit");
    }

    private int checkInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Error: Please enter a valid number: ");
            }
        }
    }

    private int checkCount() {
        while (true) {
            int count = checkInt();
            if (count > 0) {
                return count;
            }
            System.out.print("Error: Please enter a positive integer: ");
        }
    }
}
