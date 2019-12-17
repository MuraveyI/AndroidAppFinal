package com.murav.coffeeapp.service;


import com.murav.coffeeapp.model.CoffeeModel;

import java.util.List;

public interface CoffeeService {
    void addCoffee(String title, String description);

    CoffeeModel getById(int coffeeModelId);

    List<CoffeeModel> getAllCoffee();

    void deleteAll();
}
