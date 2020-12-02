package com.practice.react.service;

import com.practice.react.entity.UserDetail;
import com.practice.react.model.*;
import com.practice.react.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;

    public Flux<User> getAllUser(){
        // blocking call 부분을 별도의 쓰레드에서 처리하기 위한 방법
        return Mono.fromCallable(userRepository::findAllByQueryDsl)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.elastic());

        // blocking call
//        return Flux.fromIterable(userRepository.findAllByQueryDsl())
//                .collectList()
//                .subscribeOn(Schedulers.elastic()); // block I/O 대응
    }

    public Flux<User> getAllUser(int blockCnt, int page, Long id, String name){
        // blocking call 부분을 별도의 쓰레드에서 처리하기 위한 방법
        return Mono.fromCallable(() -> userRepository.findUserWithPaging(blockCnt, page, id, name))
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.elastic());
//        return Flux.fromIterable(userRepository.findUserWithPaging(blockCnt, page, id, name))
//                .collectList()
//                .subscribeOn(Schedulers.elastic()); // block I/O 대응
    }

    public Flux<UserDetailCnt> userListDetailCount(){
        return Mono.fromCallable(userRepository::findUserDetailCnt)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.elastic());
//        return Flux.fromIterable(userRepository.findUserDetailCnt())
//                .collectList()
//                .subscribeOn(Schedulers.elastic());
    }

    public Flux<UserDetailCnt> userListHasDetail(){
        return Mono.fromCallable(userRepository::findUserHasDetailCnt)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.elastic());
//        return Flux.fromIterable(userRepository.findUserHasDetailCnt())
//                .collectList()
//                .subscribeOn(Schedulers.elastic());
    }

    public Mono<UpdateResponse> updateUser(Long id, int age){
        return Mono.fromCallable(() -> userRepository.updateUserAge(id, age))
                .map(isUpdate ->{
                    if(!isUpdate){
                        UserDetail userDetail = UserDetail.builder().userId(id).age(age).build();
                        isUpdate = userRepository.insertUserDetail(userDetail);
                    }

                    return UpdateResponse.builder()
                            .success(isUpdate)
                            .status(isUpdate ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(isUpdate ? "수정 성공" : "수정 실패")
                            .build();
                }).subscribeOn(Schedulers.elastic());
    }

    public Mono<UpdateResponse> updateUserParallel(Long id, int age){
        Mono<Boolean> updateUserAge = Mono.fromCallable(() -> userRepository.updateUserAge(id, age)).subscribeOn(Schedulers.elastic());
        Mono<Boolean> updateUserDetailAddressNull = Mono.fromCallable(() -> userRepository.updateUserDetailAddressNull(id)).subscribeOn(Schedulers.elastic());

        return Mono.zip(updateUserAge, updateUserDetailAddressNull)
                .map(isUpdate ->{
                    log.info("isUpdate > " + isUpdate);

                    return UpdateResponse.builder()
                            .success(true)
                            .status(true ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(true ? "수정 성공" : "수정 실패")
                            .build();
                }).subscribeOn(Schedulers.elastic());
    }


    public Mono<UpdateResponse> updateUserPost(User user){
        return Mono.fromCallable(() -> userRepository.updateUserAge(user.getUserId(), user.getAge()))
                .map(isUpdate -> UpdateResponse.builder()
                        .success(isUpdate)
                        .status(isUpdate ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(isUpdate ? "수정 성공" : "수정 실패")
                        .build()
                )
                .subscribeOn(Schedulers.elastic());
    }

    public Mono<UserCount> getUserCount(){
        return Mono.fromCallable(userRepository::countUser)
                .map(count -> UserCount.builder().count(count).build())
                .subscribeOn(Schedulers.elastic());
    }

    public Mono<UpdateResponse> deleteUser(Long id){
        return Mono.fromCallable(() -> userRepository.deleteUser(id))
                .map(isUpdate ->{
                    return UpdateResponse.builder()
                            .success(isUpdate)
                            .status(isUpdate ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(isUpdate ? "삭제 성공" : "삭제 실패")
                            .build();
                }).subscribeOn(Schedulers.elastic());
    }

    public Flux<UserDate> currentDate() {
        return Mono.fromCallable(userRepository::currentDate)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.elastic());

//        return Flux.fromIterable(userRepository.currentDate())
//                .collectList()
//                .subscribeOn(Schedulers.elastic());
    }
}
