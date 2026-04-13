package com.team.project;

import com.team.project.controller.MenuController;
import com.team.project.service.impl.CarService;
import com.team.project.io.DataHandler;
import com.team.project.service.impl.SearchServiceImpl;
import com.team.project.strategy.SortContext;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        SearchServiceImpl searchService = new SearchServiceImpl();
        DataHandler dataHandler = new DataHandler();
        SortContext sortContext = new SortContext();

        CarService carService = new CarService(searchService, dataHandler, sortContext);

        MenuController menuController = new MenuController(scanner, carService);
        menuController.start();
    }
}
