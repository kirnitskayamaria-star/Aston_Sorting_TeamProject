package com.team.project.io;

import com.team.project.model.Car;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileFill implements FillStrategy {

    private final String path;

    public FileFill(String path) {
        this.path = path;
    }

    private Car parseCar(String line) {
        if (line == null || line.trim().isEmpty()) return null;

        try {
            String[] parts = line.split(",");
            if (parts.length < 3) return null;

            String m = parts[0].trim();
            String p = parts[1].trim();
            String y = parts[2].trim();

            if (!com.team.project.validation.CarValidator.validateAll(m, p, y)) {
                return null;
            }
            return new com.team.project.model.Car.CarBuilder()
                    .model(m)
                    .power(Integer.parseInt(p))
                    .year(Integer.parseInt(y))
                    .build();

        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<Car> fill(int count) {
        List<Car> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null && list.size() < count) {
                Car car = parseCar(line);
                if (car != null) {
                    list.add(car);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return list;
    }
}
