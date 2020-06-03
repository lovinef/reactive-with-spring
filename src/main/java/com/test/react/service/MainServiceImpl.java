package com.test.react.service;

import com.test.react.model.User;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService{
    @Override
    public User setUser(String name, int age) {
        return User.builder()
                .name(name)
                .age(age)
                .build();
    }

    @Override
    public User postSetUser(User user) {
        String address = "판교 주소";

        return User.builder()
                .name(user.getName())
                .age(user.getAge())
                .address(address)
                .build();
    }
}
