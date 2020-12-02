package com.practice.react.controller;

import com.practice.react.model.User;
import com.practice.react.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {
    private final MainService mainService;

    @GetMapping("/hello")
    public Mono<String> main(){
        return Mono.just("test");
    }

    @GetMapping("/hello/{name}/{age}")
    public Mono<User> main(@PathVariable("name") String name,
                           @PathVariable("age") int age){
        return Mono.just(mainService.setUser(name, age));
    }

    @PostMapping("/user")
    public Mono<User> postUser(@RequestBody User user){
        return Mono.just(mainService.postSetUser(user));
    }
}

