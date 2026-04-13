package com.team.project;

import com.team.project.controller.MenuController;
import com.team.project.io.DataHandler;
import com.team.project.service.SearchService;
import com.team.project.service.impl.CarService;
import com.team.project.service.impl.SearchServiceImpl;
import com.team.project.strategy.SortContext;

import java.util.Scanner;

public class Main {

    private static MenuController init() {
        Scanner scanner = new Scanner(System.in);
        SearchService searchService = new SearchServiceImpl();
        DataHandler dataHandler = new DataHandler();
        SortContext sortContext = new SortContext();

        CarService carService = new CarService(searchService, dataHandler, sortContext);

        return new MenuController(scanner, carService);
    }


    public static void main(String[] args) {
        MenuController menuController = init();
        menuController.start();
    }


}
