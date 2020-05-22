package com.test.react.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.react.Model.User;
import com.test.react.Model.UserDate;
import com.test.react.Model.UserDetailCnt;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.test.react.Entity.QUser.user;
import static com.test.react.Entity.QUserDetail.userDetail;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> findAllByQueryDsl() {
        // DTO로 결과값 반환
        return jpaQueryFactory
                .select(Projections.fields(
                    com.test.react.Model.User.class,
                    user.id.as("userId"),
                    user.name.as("name"),
                    userDetail.id.as("userDetailId"),
                    userDetail.age.as("age"),
                    userDetail.address.as("address")
                )).from(user)
                .leftJoin(userDetail)
                    .on(userDetail.userId.eq(user.id))
                .fetch();
    }

    @Override
    public List<UserDetailCnt> findUserHasDetailCnt() {
//        select
//        user0_.user_id as col_0_0_,
//                user0_.name as col_1_0_,
//        (select
//        count(userdetail1_.id)
//        from
//        user_detail userdetail1_
//        where
//        userdetail1_.user_id=user0_.user_id) as col_2_0_
//        from
//        user user0_
//        where
//                (
//                        select
//                        count(userdetail2_.id)
//                        from
//                        user_detail userdetail2_
//                        where
//                        userdetail2_.user_id=user0_.user_id
//                        group by
//                        userdetail2_.user_id
//                )>?
        return jpaQueryFactory
                .select(Projections.fields(
                        UserDetailCnt.class,
                    user.id.as("id"),
                    user.name.as("name"),
                    ExpressionUtils.as(
                            JPAExpressions
                                .select(userDetail.count())
                                .from(userDetail)
                                .where(userDetail.userId.eq(user.id))
                            , "detailCnt"
                    )
                ))
                .from(user)
                .where(JPAExpressions
                        .select(userDetail.count())
                        .from(userDetail)
                        .where(userDetail.userId.eq(user.id))
                        .groupBy(userDetail.userId)
                        .gt(0L)
                )
                .fetch();
    }

    @Override
    public List<UserDetailCnt> findUserDetailCnt() {
        return jpaQueryFactory
                .select(Projections.fields(
                    UserDetailCnt.class,
                    user.id.as("id"),
                    user.name.as("name"),
                    ExpressionUtils.as(
                            JPAExpressions
                                    .select(userDetail.count())
                                    .from(userDetail)
                                    .where(userDetail.userId.eq(user.id))
                            , "detailCnt"
                )
                )).from(user)
                .fetch();
    }

    @Override
    public List<User> findUserWithPaging(int blockCnt, int page) {
        // page 처리는 0부터 시작되므로, -1 처리를 해야한다.
        if(page <= 0) page = 0;
        else page = page - 1;

        return jpaQueryFactory
                .select(Projections.fields(
                        com.test.react.Model.User.class,
                        user.id.as("userId"),
                        user.name.as("name"),
                        userDetail.id.as("userDetailId"),
                        userDetail.age.as("age"),
                        userDetail.address.as("address")
                )).from(user)
                .leftJoin(userDetail)
                .on(userDetail.userId.eq(user.id))
                .offset((page <= 0) ? 0 : (page -1))
                .limit(blockCnt)
                .fetch();
    }

    @Override
    public List<User> findUserByFromSubQuery() {
        return null;
    }

    @Override
    public List<UserDate> currentDate() {
        return jpaQueryFactory
                .select(Projections.fields(
                        UserDate.class
                        , user.id.as("id")
                        , user.name.as("name")
                        , Expressions.dateTemplate(Date.class, "{0}", Expressions.currentDate()).as("date") // current_timestamp as col_2_0_
                ))
                .from(user)
                .fetch();
    }

    @Override
    @Transactional
    public boolean updateUserAge(Long id, int age) {
        long execute = jpaQueryFactory
                .update(user)
                .where(user.id.eq(id))
                .execute();

        return execute > 0;
    }

    @Override
    public Long countUser() {
        //    select
        //        count(*) as col_0_0_
        //    from
        //        user user0_
        return jpaQueryFactory
                .from(user)
                .fetchCount();
    }



    @Override
    @Transactional
    public boolean deleteUser(Long id) {
//        delete
//                from
//        user
//                where
//        id=?
        long execute = jpaQueryFactory
                .delete(user)
                .where(user.id.eq(id))
                .execute();

        return execute > 0;
    }


}
