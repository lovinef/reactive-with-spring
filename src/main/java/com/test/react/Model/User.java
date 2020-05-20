package com.test.react.Model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long userId;
    private String name;

    private Long userDetailId;
    private int age;
    private String address;
}
