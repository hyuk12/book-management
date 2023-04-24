package com.toyproject.bookmanagement.api.dto.response.auth;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class PrincipalRespDto {
    private int userId;
    private String email;
    private String name;
    private String authorities;
}
