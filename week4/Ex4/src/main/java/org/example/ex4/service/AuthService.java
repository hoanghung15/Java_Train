package org.example.ex4.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.ex4.dto.request.LoginRequest;
import org.example.ex4.dto.response.ApiResponse;
import org.example.ex4.dto.response.AuthResponse;
import org.example.ex4.entity.User;
import org.example.ex4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    @NonFinal
    @Value("${jwt.access.key}")
    String accessKey;
    @NonFinal
    @Value("${jwt.refresh.key}")
    String refreshKey;
    @NonFinal
    @Value("${jwt.access.expire}")
    int expAccess;
    @Value("${jwt.refresh.expire}")
    @NonFinal
    int expRefresh;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    TokenService tokenService;

    public ApiResponse<AuthResponse> login(LoginRequest request) {
        boolean checkUsername = userRepository.existsByUsername(request.getUsername());
        if (!checkUsername) throw new UsernameNotFoundException("Username not found");
        User user = userRepository.findByUsername(request.getUsername());
        boolean matchPass = passwordEncoder.matches(request.getPassword(), user.getPassword());
        String accessToken = "";
        String refreshToken = "";
        if (matchPass) {
            accessToken = jwtService.generateToken(user, true, expAccess, accessKey);
            refreshToken = jwtService.generateToken(user, false, expRefresh, refreshKey);
            tokenService.saveTokenToRedis(user.getUsername(), refreshToken);
            tokenService.saveTokenToRedis(user.getUsername(), accessToken);
            log.info("User's Token in Redis", tokenService.getTokenFromRedis(user.getUsername()));
        }
        AuthResponse authResponse = AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return ApiResponse.<AuthResponse>builder()
                .code(200)
                .message("Success")
                .result(authResponse)
                .build();
    }
}
