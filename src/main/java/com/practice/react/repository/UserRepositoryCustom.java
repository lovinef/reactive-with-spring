package com.practice.react.repository;


import com.practice.react.entity.UserDetail;
import com.practice.react.model.User;
import com.practice.react.model.UserDate;
import com.practice.react.model.UserDetailCnt;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findAllByQueryDsl();
    List<UserDetailCnt> findUserHasDetailCnt();
    List<UserDetailCnt> findUserDetailCnt();
    List<User> findUserWithPaging(int blockCnt, int page, Long id, String name);
    List<User> findUserByFromSubQuery();

    boolean insertUserDetail(UserDetail userDetail);
    boolean updateUserAge(Long id, int age);
    boolean updateUserDetailAddressNull(Long id);
    boolean deleteUser(Long id);
    Long countUser();

    List<UserDate> currentDate();
}
