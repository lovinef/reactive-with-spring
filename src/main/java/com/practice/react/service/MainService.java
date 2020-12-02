package com.practice.react.service;


import com.practice.react.model.User;

public interface MainService {
    User setUser(String name, int age);
    User postSetUser(User user);
}
