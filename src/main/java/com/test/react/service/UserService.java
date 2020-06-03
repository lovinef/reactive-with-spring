package com.test.react.service;

import com.test.react.model.*;
import com.test.react.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService{
    private final UserRepository userRepository;

    public Mono<List<User>> getAllUser(){
        // blocking call 부분을 별도의 쓰레드에서 처리하기 위한 방법
        return Mono.fromCallable(userRepository::findAllByQueryDsl)
                .flatMapMany(Flux::fromIterable)
                .collectList()
                .subscribeOn(Schedulers.elastic());

        // blocking call
//        return Flux.fromIterable(userRepository.findAllByQueryDsl())
//                .collectList()
//                .subscribeOn(Schedulers.elastic()); // block I/O 대응
    }

    public Mono<List<User>> getAllUser(int blockCnt, int page, Long id, String name){
        return Flux.fromIterable(userRepository.findUserWithPaging(blockCnt, page, id, name))
                .collectList()
                .subscribeOn(Schedulers.elastic()); // block I/O 대응
    }

    public Mono<List<UserDetailCnt>> userListDetailCount(){
        return Flux.fromIterable(userRepository.findUserDetailCnt())
                .collectList()
                .subscribeOn(Schedulers.elastic());
    }

    public Mono<List<UserDetailCnt>> userListHasDetail(){
        return Flux.fromIterable(userRepository.findUserHasDetailCnt())
                .collectList()
                .subscribeOn(Schedulers.elastic());
    }

    public Mono<UpdateResponse> updateUser(Long id, int age){
        return Mono.fromCallable(() -> userRepository.updateUserAge(id, age))
                .map(isUpdate ->{
                    return UpdateResponse.builder()
                            .success(isUpdate)
                            .status(isUpdate ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(isUpdate ? "수정 성공" : "수정 실패")
                            .build();
                }).subscribeOn(Schedulers.elastic());
    }

    public Mono<UpdateResponse> updateUserPost(User user){
        return Mono.fromCallable(() -> userRepository.updateUserAge(user.getUserId(), user.getAge()))
                .map(isUpdate ->{
                    return UpdateResponse.builder()
                            .success(isUpdate)
                            .status(isUpdate ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(isUpdate ? "수정 성공" : "수정 실패")
                            .build();
                }).subscribeOn(Schedulers.elastic());
    }

    public Mono<UserCount> getUserCount(){
        return Mono.fromCallable(userRepository::countUser)
                .map(count ->{
                    return UserCount.builder().count(count).build();
                }).subscribeOn(Schedulers.elastic());
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

    public Mono<List<UserDate>> currentDate() {
        return Flux.fromIterable(userRepository.currentDate())
                .collectList()
                .subscribeOn(Schedulers.elastic());
    }
}
