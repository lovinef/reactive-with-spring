package com.test.react.controller;

import com.test.react.model.*;
import com.test.react.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public Mono<List<User>> list(){
        return userService.getAllUser();
    }

    @GetMapping("/list/none")
    public Mono<List<User>> listNone(){
        return null;
    }

    @GetMapping("/list/paging")
    public Mono<List<User>> listPaging(
            @RequestParam(name="blockCnt") int blockCnt,
            @RequestParam(name="page") int page
    ){
        return userService.getAllUser(blockCnt, page, null, null);
    }

    @GetMapping("/list/{blockCnt}/{page}")
    public Mono<List<User>> getAllUser(
            @PathVariable(name="blockCnt") int blockCnt,
            @PathVariable(name="page") int page
    ){
        return userService.getAllUser(blockCnt, page, null, null);
    }

    @GetMapping("/list/count")
    public Mono<UserCount> userListCount(){
        return userService.getUserCount();
    }

    @GetMapping("/list/detail/count")
    public Mono<List<UserDetailCnt>> userListDetailCount(){
        return userService.userListDetailCount();
    }

    @GetMapping("/list/hasDetail")
    public Mono<List<UserDetailCnt>> userListHasDetail(){
        return userService.userListHasDetail();
    }

    @GetMapping("/update/{id}/{age}")
    public Mono<UpdateResponse> updateUser(
            @PathVariable("id") Long id,
            @PathVariable("age") int age
    ){
        return userService.updateUser(id, age);
    }

    @GetMapping("/update/p/{id}/{age}")
    public Mono<UpdateResponse> updateUserParallel(
            @PathVariable("id") Long id,
            @PathVariable("age") int age
    ){
        return userService.updateUserParallel(id, age);
    }

    @PostMapping("/update")
    public Mono<UpdateResponse> updateUserPost(@RequestBody User user){
        return userService.updateUserPost(user);
    }


    @ApiOperation(
            value = "사용자 삭제",
            notes = "해당 ID의 사용자를 삭제합니다.\n",
            response = UpdateResponse.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true,  dataType = "long", paramType = "query", value = "사용자 ID ", defaultValue = "0"),
    })
    @GetMapping("/delete/{id}")
    public Mono<UpdateResponse> deleteUser(
            @PathVariable("id") Long id
    ){
        return userService.deleteUser(id);
    }

    @ApiOperation(
            value = "현재 날짜 조회",
            notes = "현재 날짜를 조회합니다.\n",
            response = UserDate.class
    )
    @GetMapping("/currentDate")
    public Mono<List<UserDate>> currentDate(){
        return userService.currentDate();
    }
}
