package com.test.react.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.react.Entity.QUser;
import com.test.react.Entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> findAllByQueryDsl() {
        QUser qUser = QUser.user;

        return jpaQueryFactory.selectFrom(qUser).fetch();
    }
}
