package com.murav.coffeeapp.service;

import android.net.Uri;

import com.murav.coffeeapp.model.User;

public interface UserService {
    void save(String name, String email, Uri photoUrl);

    void deleteAll();

    User getOneUser();
}
