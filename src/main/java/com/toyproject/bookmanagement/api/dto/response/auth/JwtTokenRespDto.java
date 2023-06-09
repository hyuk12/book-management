package com.toyproject.bookmanagement.api.dto.response.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class JwtTokenRespDto {
    private String grantType;
    private String accessToken;

}
