package com.test.react.Entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;
import reactor.util.annotation.Nullable;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private int age;
    private String address;
}
