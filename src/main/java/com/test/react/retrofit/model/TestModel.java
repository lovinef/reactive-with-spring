package com.test.react.retrofit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestModel {
    private Long userId;
    private String name;

    private Long userDetailId;
    private int age;
    private String address;
}
