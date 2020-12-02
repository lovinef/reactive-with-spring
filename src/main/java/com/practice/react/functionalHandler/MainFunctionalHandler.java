package com.practice.react.functionalHandler;

import com.practice.react.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MainFunctionalHandler {
    public Mono<ServerResponse> mainHandler(ServerRequest request){
        Mono<String> mono = Mono.just("hello");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono, String.class);
    }

    public Mono<ServerResponse> mainHelloHandler(ServerRequest request){
        String name = request.pathVariable("name");
        int age = Integer.parseInt(request.pathVariable("age"));

        Mono<User> mono = Mono.just(User.builder().name(name).age(age).build());
        return ServerResponse.ok().body(mono, User.class);
    }

    public Mono<ServerResponse> mainPostHelloHandler(ServerRequest request){
        String address = "판교 주소";
        Mono<User> userMono = request.bodyToMono(User.class)
                .map(user -> {
                    return user;
                });

        return ServerResponse.ok().body(userMono, User.class);
    }
}
