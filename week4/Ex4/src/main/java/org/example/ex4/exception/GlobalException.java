package org.example.ex4.exception;

import org.example.ex4.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = UserExistedException.class)
    ResponseEntity<ApiResponse> handleUserExistedException(UserExistedException e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .message(e.getMessage())
                .code(400)
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
