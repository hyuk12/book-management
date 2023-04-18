package com.toyproject.bookmanagement.api.controller.advice;

import com.toyproject.bookmanagement.api.dto.common.ErrorRespDto;
import com.toyproject.bookmanagement.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) {
        return ResponseEntity.badRequest().body(new ErrorRespDto<>(e.getMessage(), e.getErrorMap()));
    }
}
