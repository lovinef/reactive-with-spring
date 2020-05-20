package com.test.react.Model;

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
