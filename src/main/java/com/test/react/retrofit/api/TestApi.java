package com.test.react.retrofit.api;

import com.test.react.retrofit.model.TestModel;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface TestApi {
    @GET("/api/user/list")
    Call<List<TestModel>> getTestModelList();

    @GET("/api/user/list/none")
    Call<List<TestModel>> getTestModelListNone();
}
