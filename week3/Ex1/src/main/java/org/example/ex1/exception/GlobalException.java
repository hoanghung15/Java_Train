package org.example.ex1.exception;

import org.example.ex1.Util.ProductNotExist;
import org.example.ex1.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = ProductNotExist.class)
    ResponseEntity<ApiResponse>handleProductNotExist(ProductNotExist e) {
        ApiResponse apiResponse =  ApiResponse.builder()
                .message(e.getMessage())
                .status(400)
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse>handleException(RuntimeException e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .message(e.getMessage())
                .status(400)
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse>handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .message(e.getMessage())
                .status(400)
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
