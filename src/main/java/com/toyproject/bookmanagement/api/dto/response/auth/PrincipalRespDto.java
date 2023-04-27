package com.toyproject.bookmanagement.api.dto.response.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PrincipalRespDto {
    private int memberId;
    private String email;
    private String name;
    private String authorities;
}
