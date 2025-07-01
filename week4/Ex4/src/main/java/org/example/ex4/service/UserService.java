package org.example.ex4.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.ex4.dto.request.UserCreationRequest;
import org.example.ex4.dto.response.ApiResponse;
import org.example.ex4.entity.User;
import org.example.ex4.exception.UserExistedException;
import org.example.ex4.mapper.Mapping;
import org.example.ex4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    Mapping mapping;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    TokenService tokenService;

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

    public ApiResponse createUser(UserCreationRequest request) {
        boolean checkExistsUserName = userRepository.existsByUsername(request.getUsername());
        boolean checkExistsEmail = userRepository.existsByEmail(request.getEmail());
        if (checkExistsUserName || checkExistsEmail) {
            throw new UserExistedException("Username or Email already exists");
        }
        User user = mapping.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);

        return ApiResponse.builder()
                .code(200)
                .message("Successfully created user")
                .result(user)
                .build();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
