package com.test.react.Service;

import com.test.react.Entity.User;

public interface MainService {
    User setUser(String name, int age);
    User postSetUser(User user);
}
