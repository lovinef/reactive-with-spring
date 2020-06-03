package com.test.react.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailCnt {
    private Long id;
    private String name;
    private Long detailCnt;
}
