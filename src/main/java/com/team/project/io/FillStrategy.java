package com.team.project.io;

import com.team.project.model.Car;
import java.util.List;

public interface FillStrategy {
    List<Car> fill(int count);
}