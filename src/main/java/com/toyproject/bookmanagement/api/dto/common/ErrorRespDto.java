package com.toyproject.bookmanagement.api.dto.common;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorRespDto<T> {
    private String message;
    private T errorData;
}
