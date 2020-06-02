package com.test.react.Controller;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.test.react.Model.*;
import com.test.react.Service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import springfox.documentation.service.ResponseMessage;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public Mono<List<User>> main(){
        return userService.getAllUser();
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
