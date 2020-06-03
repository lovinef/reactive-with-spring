package com.test.react.service;


import com.test.react.model.User;

public interface MainService {
    User setUser(String name, int age);
    User postSetUser(User user);
}
