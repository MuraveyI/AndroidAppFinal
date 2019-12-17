
package com.murav.coffeeapp.service;


import com.murav.coffeeapp.model.CoffeeModel;
import com.murav.coffeeapp.repository.CoffeeRepository;
import com.murav.coffeeapp.repository.CoffeeRepositoryImpl;

import java.util.List;
import java.util.Random;

public class CoffeeServiceImpl implements CoffeeService {

    private CoffeeRepository coffeeRepository = new CoffeeRepositoryImpl();

    @Override
    public void addCoffee(String name, String description) {

        System.out.println(name + "  " + description);

        int nextId = coffeeRepository.getNextCoffeeId();

        CoffeeModel coffeeModel = new CoffeeModel();
        Random rand = new Random();
        coffeeModel.setImage("img" + (rand.nextInt(5) + 1));
        coffeeModel.setId(nextId);
        coffeeModel.setName(name);
        coffeeModel.setDescription(description);

        coffeeRepository.addCoffee(coffeeModel);
    }

    @Override
    public List<CoffeeModel> getAllCoffee() {
        return coffeeRepository.getAllCoffee();
    }

    @Override
    public CoffeeModel getById(int coffeeModelId) {
        return coffeeRepository.getById(coffeeModelId);
    }

    @Override
    public void deleteAll() {
        coffeeRepository.deleteAll();
    }
}

