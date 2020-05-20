package com.test.react.Model;

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
