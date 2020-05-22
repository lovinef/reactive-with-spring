package com.test.react.Model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long userId;
    private String name;

    private Long userDetailId;
    private int age;
    private String address;

    public User(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
