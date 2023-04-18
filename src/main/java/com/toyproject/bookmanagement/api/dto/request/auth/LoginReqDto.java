package com.toyproject.bookmanagement.api.dto.request.auth;

import lombok.Getter;

@Getter
public class LoginReqDto {
    private String email;
    private String password;
}
