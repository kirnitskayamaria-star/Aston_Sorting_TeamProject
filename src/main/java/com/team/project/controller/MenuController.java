package com.team.project.controller;

import com.team.project.Car;
import com.team.project.service.SearchService;
import com.team.project.service.impl.SearchServiceImpl;

import java.util.List;
import java.util.Scanner;

public class MenuController {

    private final Scanner scanner = new Scanner(System.in);
    private final SearchService searchService =  new SearchServiceImpl();
    private final SortContext sortContext = new SortContext();
    private FillStrategy fillStrategy;
    private final DataHandler dataHandler = new DataHandler();
    private List<Car> cars;


    public void start() {
        int choice;
        while (true) {
            mainMenu();
            choice = scanner.nextInt();
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

    private void mainMenu() {
        System.out.println("\n=========Main menu==========");
        System.out.println("1. Fill car list");
        System.out.println("2. Write car list to file");
        System.out.println("3. Sort car list");
        System.out.println("4. Count the car in list");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private void fillMenu() {
        System.out.println("\nChoose a way to fill the car list: ");
        System.out.println("1. Read from file");
        System.out.println("2. Random");
        System.out.println("3. Manual");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> readFile();
            case 2 -> readRandom();
            case 3 -> readManual();
            default -> System.out.println("Error: invalid choice");
        }
    }

    private void readFile() {
        System.out.print("Input file name: ");
        String fileName = scanner.nextLine();

        System.out.print("Input car amount: ");
        int countCars = scanner.nextInt();
        scanner.nextLine();

        fillStrategy = new FillFile(fileName);
        cars = fillStrategy.fill(countCars);
    }

    private void readRandom() {
        System.out.print("Input car amount: ");
        int countCars = scanner.nextInt();
        scanner.nextLine();

        fillStrategy = new RandomFill();
        cars = fillStrategy.fill(countCars);
    }

    private void writeFileMenu() {
        if (cars == null || cars.isEmpty()) {
            System.out.println("Car list is empty");
            return;
        }
        System.out.print("Input file name: ");
        String fileName = scanner.nextLine();

        dataHandler.save(fileName, cars);
    }

    private void readManual() {
        System.out.print("Input car amount: ");
        int countCars = scanner.nextInt();
        scanner.nextLine();

        fillStrategy = new ManualFill();
        cars = fillStrategy.fill(countCars);
    }

    private void sortMenu() {
        if (cars == null || cars.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        System.out.println("\nChoose a sort order:");
        System.out.println("1. By model");
        System.out.println("2. By power");
        System.out.println("3. By year production");
        System.out.println("4. Sort cars with even power");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> sortContext.setStrategy(new CarSortingStrategies.SortByModel());
            case 2 -> sortContext.setStrategy(new CarSortingStrategies.SortByPower());
            case 3 -> sortContext.setStrategy(new CarSortingStrategies.SortByYear());
            case 4 -> sortContext.setStrategy(new EvenSortStrategy());
            default -> {
                System.out.println("Error: invalid choice");
                return;
            }
        }

        try {
            sortContext.sort(cars);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchMenu() {
        System.out.print("Input car model: ");
        String model = scanner.nextLine();
        System.out.println("Amount of cars: " + searchService.countCars(cars, model));
    }

}
