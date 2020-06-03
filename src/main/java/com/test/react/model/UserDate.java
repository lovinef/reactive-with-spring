package com.test.react.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDate {
    private Long id;
    private String name;
    private String date;
}
