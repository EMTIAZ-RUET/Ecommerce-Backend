package com.ironsoftware.userservice.controller;

import com.ironsoftware.userservice.dto.LoginRequest;
import com.ironsoftware.userservice.dto.SignupRequest;
import com.ironsoftware.userservice.dto.JwtResponse;
import com.ironsoftware.userservice.dto.UserResponse;
import com.ironsoftware.userservice.dto.UserProfileUpdateRequest;
import com.ironsoftware.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }

    @PostMapping("/users/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        return userService.registerUser(signupRequest);
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserProfile(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }
    
    @PutMapping("/users/{userId}")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUserProfile(
            @PathVariable Long userId,
            @Valid @RequestBody UserProfileUpdateRequest updateRequest) {
        return userService.updateUserProfile(userId, updateRequest);
    }
}
