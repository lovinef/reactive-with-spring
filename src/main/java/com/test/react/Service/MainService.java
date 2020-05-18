package com.test.react.Service;


import com.test.react.Model.User;

public interface MainService {
    User setUser(String name, int age);
    User postSetUser(User user);
}
