package com.digio.challenge.infrastructure.exceptionhandler;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse {

    private String title;
    private Integer status;
    private String detail;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

}
