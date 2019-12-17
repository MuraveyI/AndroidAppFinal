package com.murav.coffeeapp.service;

import android.net.Uri;

import com.murav.coffeeapp.model.User;
import com.murav.coffeeapp.repository.UserRepository;
import com.murav.coffeeapp.repository.UserRepositoyrImpl;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepositoyrImpl();


    @Override
    public void save(String name, String email, Uri photoUrl) {
        User user = new User();
        int nextUserId = userRepository.getNextUserId();

        user.setId(nextUserId);
        user.setName(name);
        user.setEmail(email);
        String phoneUrlValue = (photoUrl != null) ? photoUrl.toString() : "url";
        user.setPhotoUrl(phoneUrlValue);

        userRepository.save(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User getOneUser() {
        return userRepository.getOneUser();
    }
}
