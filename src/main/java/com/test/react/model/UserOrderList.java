package com.test.react.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderList {
    private Long id;
    private String name;
    private Long orderId;
    private String orderName;
    private int amount;
    private String dateTime;
}
