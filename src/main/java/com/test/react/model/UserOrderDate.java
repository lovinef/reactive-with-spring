package com.test.react.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;

@ToString
@Getter
@AllArgsConstructor
public class UserOrderDate {
    private BigInteger userId;
    private String orderDate;
}
