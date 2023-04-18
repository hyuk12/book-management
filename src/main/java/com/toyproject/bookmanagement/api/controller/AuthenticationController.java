package com.toyproject.bookmanagement.api.controller;

import com.toyproject.bookmanagement.aop.annotation.ValidAspect;
import com.toyproject.bookmanagement.api.dto.request.auth.LoginReqDto;
import com.toyproject.bookmanagement.api.dto.request.auth.SignupReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto loginReqDto){
        return ResponseEntity.ok(null);
    }

    @CrossOrigin
    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupReqDto signupReqDto, BindingResult bindingResult) {
        return ResponseEntity.ok(bindingResult);
    }
}
