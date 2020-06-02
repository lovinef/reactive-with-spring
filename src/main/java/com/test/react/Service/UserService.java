package com.test.react.Service;

import com.test.react.Model.*;
import com.test.react.repository.UserRepository;
import lombok.Builder;
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
        return Flux.fromIterable(userRepository.findAllByQueryDsl())
                .collectList()
                .subscribeOn(Schedulers.elastic()); // block I/O 대응
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
        return Mono.just(userRepository.updateUserAge(id, age)).map(isUpdate ->{
                return UpdateResponse.builder()
                        .success(isUpdate)
                        .status(isUpdate ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(isUpdate ? "수정 성공" : "수정 실패")
                        .build();
        }).subscribeOn(Schedulers.elastic());
    }

    public Mono<UpdateResponse> updateUserPost(User user){
        return Mono.just(userRepository.updateUserAge(user.getUserId(), user.getAge())).map(isUpdate ->{
            return UpdateResponse.builder()
                    .success(isUpdate)
                    .status(isUpdate ? HttpStatus.OK.value() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(isUpdate ? "수정 성공" : "수정 실패")
                    .build();
        }).subscribeOn(Schedulers.elastic());
    }

    public Mono<UserCount> getUserCount(){
        return Mono.just(userRepository.countUser()).map(count ->{
            return UserCount.builder().count(count).build();
        }).subscribeOn(Schedulers.elastic());
    }

    public Mono<UpdateResponse> deleteUser(Long id){
        return Mono.just(userRepository.deleteUser(id)).map(isUpdate ->{
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
