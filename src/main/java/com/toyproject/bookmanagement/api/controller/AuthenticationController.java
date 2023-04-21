package com.toyproject.bookmanagement.api.controller;

import com.toyproject.bookmanagement.aop.annotation.ValidAspect;
import com.toyproject.bookmanagement.api.dto.request.auth.LoginReqDto;
import com.toyproject.bookmanagement.api.dto.request.auth.SignupReqDto;
import com.toyproject.bookmanagement.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @ValidAspect
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto loginReqDto, BindingResult bindingResult){
        return ResponseEntity.ok(authenticationService.login(loginReqDto));
    }

    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupReqDto signupReqDto, BindingResult bindingResult) {
        authenticationService.duplicatedEmailCheck(signupReqDto.getEmail());
        authenticationService.save(signupReqDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/authenticated")
    public ResponseEntity<?> authenticated(String accessToken) {
        return ResponseEntity.ok(authenticationService.authenticated(accessToken));
    }
}
