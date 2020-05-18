package com.test.react.Service;

import com.test.react.Model.User;
import com.test.react.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
                .map(findUser ->{
                    return User.builder()
                            .name(findUser.getName())
                            .age(findUser.getAge())
                            .build();
                }).collectList()
                .subscribeOn(Schedulers.elastic()); // block I/O 대응
    }
}
