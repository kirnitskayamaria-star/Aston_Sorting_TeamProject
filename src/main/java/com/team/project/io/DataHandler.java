package com.team.project.io;

import com.team.project.collection.StreamDataService;
import com.team.project.model.Car;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private final StreamDataService streamService = new StreamDataService();
    private final UserInputAdapter adapter = new UserInputAdapter();

    public List<Car> fillByStreams(int count) {
        return streamService.fillWithStreams(count);
    }

    public List<Car> fillFromFile(String path) {
        List<Car> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Car car = FileParser.parseCar(line);
                if (car != null) list.add(car);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public void saveToFile(String path, List<Car> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            for (Car car : list) {
                writer.println(car.toString());
            }
            System.out.println("Data successfully appended to " + path);
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }

    public Car processSingleInput(String model, String power, String year) {
        return adapter.adaptInputToCar(model, power, year);
    }


}

