package com.team.project.io;

import com.team.project.model.Car;
import java.io.*;
import java.util.List;

public class DataHandler {

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


}

