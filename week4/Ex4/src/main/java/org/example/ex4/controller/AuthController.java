package org.example.ex4.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ex4.dto.request.LoginRequest;
import org.example.ex4.dto.response.ApiResponse;
import org.example.ex4.dto.response.AuthResponse;
import org.example.ex4.service.AuthService;
import org.example.ex4.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "auth-controller")
@RequestMapping("auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("login")
    public ApiResponse<AuthResponse> loginNormal(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
