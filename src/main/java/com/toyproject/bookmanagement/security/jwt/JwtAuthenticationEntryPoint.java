package com.toyproject.bookmanagement.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toyproject.bookmanagement.api.dto.common.ErrorRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ErrorRespDto<?> errorResponseDto =
                new ErrorRespDto<AuthenticationException>("토큰 인증 실패", authException);
        ObjectMapper objectMapper = new ObjectMapper();

        String responseJson = objectMapper.writeValueAsString(errorResponseDto);

        PrintWriter out = response.getWriter();
        out.println(responseJson);

    }
}
