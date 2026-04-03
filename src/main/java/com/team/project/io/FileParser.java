package com.team.project.io;

import com.team.project.model.Car;

public class FileParser {

    public static Car parseCar(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        try {
            String[] parts = line.split(",");
            if (parts.length < 3) {
                System.err.println("Invalid line format: " + line);
                return null;
            }

            String model = parts[0].trim();
            String powerStr = parts[1].trim();
            String yearStr = parts[2].trim();

            UserInputAdapter adapter = new UserInputAdapter();
            return adapter.adaptInputToCar(model, powerStr, yearStr);

        } catch (Exception e) {
            System.err.println("Error parsing line: " + line);
            return null;
        }
    }
}
