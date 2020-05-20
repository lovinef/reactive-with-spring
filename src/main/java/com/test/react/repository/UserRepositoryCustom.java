package com.test.react.repository;


import com.test.react.Model.User;
import com.test.react.Model.UserDetailCnt;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findAllByQueryDsl();
    List<UserDetailCnt> findUserHasDetailCnt();
    List<UserDetailCnt> findUserDetailCnt();
    List<User> findUserWithPaging(int blockCnt, int page);

    boolean updateUserAge(Long id, int age);
    boolean deleteUser(Long id);
    Long countUser();
}
