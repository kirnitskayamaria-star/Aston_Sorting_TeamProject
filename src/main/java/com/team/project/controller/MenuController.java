package com.team.project.controller;


import com.team.project.io.FileFill;
import com.team.project.io.ManualFill;
import com.team.project.io.RandomFill;
import com.team.project.service.impl.CarService;
import com.team.project.strategy.CarSortingStrategies;
import com.team.project.strategy.EvenSortStrategy;

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
                case 2 -> printList();
                case 3 -> writeFileMenu();
                case 4 -> sortMenu();
                case 5 -> searchMenu();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Error: invalid choice");
            }
        }
    }

    private void mainMenu() {
        System.out.println("\nMAIN MENU\n1. Fill list\n2. Print list\n3. Save list to a file\n" +
                "4. Sort list\n5. Count cars by model\n0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void fillMenu() {
        System.out.println("\n1. Read from file\n2. Random\n3. Manual");
        System.out.print("Enter your choice: ");
        int choice = checkInt();
        System.out.print("Enter amount of cars: ");
        int count = checkCount();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter file name: ");
                carService.fill(new FileFill(scanner.nextLine()), count);
            }
            case 2 -> carService.fill(new RandomFill(), count);
            case 3 -> carService.fill(new ManualFill(scanner), count);
        }
    }

    private void printList() {
        try {
            carService.printList();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sortMenu() {
        System.out.println("\nSort by:\n1. Model\n2. Power\n3. Year\n4. Custom");
        System.out.println("Enter your choice: ");
        int choice = checkInt();
        try {
            switch (choice) {
                case 1 -> carService.sort(new CarSortingStrategies.SortByModel());
                case 2 -> carService.sort(new CarSortingStrategies.SortByPower());
                case 3 -> carService.sort(new CarSortingStrategies.SortByYear());
                case 4 -> carService.sort(new EvenSortStrategy());
                default -> System.out.print("Error: invalid choice");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeFileMenu() {
        System.out.print("Enter file name: ");

        try {
            carService.save(scanner.nextLine());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchMenu() {
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        try {
            System.out.println("Found: " + carService.countModel(model));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    private int checkInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Error: enter a valid number: ");
            }
        }
    }

    private int checkCount() {
        while (true) {
            int count = checkInt();
            if (count > 0) {
                return count;
            }
            System.out.print("Error: enter a positive integer: ");
        }
    }
}
