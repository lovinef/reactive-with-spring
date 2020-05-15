package com.test.react.Controller;

import com.test.react.Entity.User;
import com.test.react.Service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
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

    @PostMapping("/post/user")
    public Mono<User> postUser(@RequestBody User user){
        return Mono.just(mainService.postSetUser(user));
    }
}
