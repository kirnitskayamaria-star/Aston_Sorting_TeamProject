package com.team.project.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.function.Supplier;

public class BuilderTestMain {
    public static List<Car> cars = new ArrayList<>();

    public static void main(String[] args) {
        Supplier<Integer> print = BuilderTestMain::print;

        BuilderTestRandom builderTestRandom = new BuilderTestRandom();
        for (int i = 0; i < 10; i++) {
            builderTestRandom.input();
            Car car = builderTestRandom.output();
            cars.add(car);
        }

        print.get();
        cars.clear();

        BuilderTestMain.getFileData(System.getProperty("user.dir") +
                "/src/data.txt");
        print.get();
        cars.clear();

        while (cars.size() < 3) {
            try {
                BuilderTestManual builderTestManual = new BuilderTestManual();
                builderTestManual.input();
                Car car = builderTestManual.output();
                cars.add(car);
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input! Try Again!");
            }
        }
        print.get();
        cars.clear();
    }

    public static int getFileData(String name) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(name),
                    StandardCharsets.UTF_8);
            for (String line : lines) {
                FileBuilder fileBuilder = new FileBuilder(line);
                Car car = fileBuilder.output();
                cars.add(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int print() {
        for (Car car : cars) {
            System.out.println(car.toString());
        }
        return 0;
    }
}
