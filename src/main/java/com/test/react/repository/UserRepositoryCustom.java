package com.test.react.repository;

import com.test.react.Entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findAllByQueryDsl();
}
