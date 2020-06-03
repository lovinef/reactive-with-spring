package com.test.react.repository;


import com.test.react.model.User;
import com.test.react.model.UserDate;
import com.test.react.model.UserDetailCnt;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findAllByQueryDsl();
    List<UserDetailCnt> findUserHasDetailCnt();
    List<UserDetailCnt> findUserDetailCnt();
    List<User> findUserWithPaging(int blockCnt, int page, Long id, String name);
    List<User> findUserByFromSubQuery();


    boolean updateUserAge(Long id, int age);
    boolean updateUserDetailAddressNull(Long id);
    boolean deleteUser(Long id);
    Long countUser();

    List<UserDate> currentDate();
}
