package com.murav.coffeeapp.repository;

import com.murav.coffeeapp.model.CoffeeModel;

import java.util.List;

public interface CoffeeRepository {

    void addCoffee(CoffeeModel coffeeModel);

    List<CoffeeModel> getAllCoffee();

    int getNextCoffeeId();

    CoffeeModel getById(int id);

    void deleteAll();

}
