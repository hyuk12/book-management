package com.toyproject.bookmanagement.api.dto.request.auth;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
public class SignupReqDto {
    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$",
            message = "비밀번호는 영문, 숫자, 특수문자를 포함한 8~16자리로 입력해주세요.")
    private String password;

    @Pattern(regexp = "^[가-힣]{2,7}$",
            message = "이름은 한글 2~7자리로 입력해주세요.")
    private String name;
}
