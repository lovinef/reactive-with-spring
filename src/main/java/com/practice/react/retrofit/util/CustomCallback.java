package com.practice.react.retrofit.util;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Slf4j
public abstract class CustomCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        System.out.println("response > " + response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        log.error(t.getMessage());
        t.printStackTrace();
    }
}
