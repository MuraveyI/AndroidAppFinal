package com.murav.coffeeapp.repository;

import com.murav.coffeeapp.model.User;

public interface UserRepository {
    void save(User user);

    void deleteAll();

    User getOneUser();

    int getNextUserId();

}
