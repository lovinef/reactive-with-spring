package com.practice.react.retrofit.api;

import com.practice.react.retrofit.model.TestModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface TestApi {
    @GET("/api/user/list")
    Call<List<TestModel>> getTestModelList();

    @GET("/api/user/list/none")
    Call<List<TestModel>> getTestModelListNone();

    @GET("/api/user/list/paging")
    Call<List<TestModel>> getPagingTestModel(@Query("blockCnt") Integer blockCnt, @Query("page") Integer page);
}
