package com.test.react.Controller;

import com.test.react.Model.*;
import com.test.react.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/user/list")
    public Mono<List<User>> main(){
        return userService.getAllUser();
    }

    @GetMapping("/user/list/{blockCnt}/{page}")
    public Mono<List<User>> getAllUser(
            @PathVariable(name="blockCnt") int blockCnt,
            @PathVariable(name="page") int page
    ){
        return userService.getAllUser(blockCnt, page);
    }

    @GetMapping("/user/list/count")
    public Mono<UserCount> userListCount(){
        return userService.getUserCount();
    }

    @GetMapping("/user/list/detail/count")
    public Mono<List<UserDetailCnt>> userListDetailCount(){
        return userService.userListDetailCount();
    }

    @GetMapping("/user/list/hasDetail")
    public Mono<List<UserDetailCnt>> userListHasDetail(){
        return userService.userListHasDetail();
    }

    @GetMapping("/user/update/{id}/{age}")
    public Mono<UpdateResponse> updateUser(
            @PathVariable("id") Long id,
            @PathVariable("age") int age
    ){
        return userService.updateUser(id, age);
    }

    @PostMapping("/user/update")
    public Mono<UpdateResponse> updateUserPost(@RequestBody User user){
        return userService.updateUserPost(user);
    }

    @GetMapping("/user/delete/{id}")
    public Mono<UpdateResponse> deleteUser(
            @PathVariable("id") Long id
    ){
        return userService.deleteUser(id);
    }

    @GetMapping("/user/currentDate")
    public Mono<List<UserDate>> currentDate(){
        return userService.currentDate();
    }
}
