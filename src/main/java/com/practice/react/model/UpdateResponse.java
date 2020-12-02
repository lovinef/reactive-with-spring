package com.practice.react.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateResponse {
    private boolean success;
    private int status;
    private String message;
}
