package com.test.react.Controller;

import com.test.react.Model.User;
import com.test.react.Service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

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

