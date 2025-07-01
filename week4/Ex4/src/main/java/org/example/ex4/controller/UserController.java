package org.example.ex4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.ex4.dto.request.UserCreationRequest;
import org.example.ex4.dto.response.ApiResponse;
import org.example.ex4.entity.User;
import org.example.ex4.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "user-controller")
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping()
    @Operation(description = "Create new User", summary = "Create user")
    public ApiResponse createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List<User> getAllUsers() {

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       log.info(authentication.getAuthorities().toString());
        return  userService.getAllUsers();
    }
}
