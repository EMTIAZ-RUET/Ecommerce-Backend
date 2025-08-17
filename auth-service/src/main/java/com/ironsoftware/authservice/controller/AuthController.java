package com.ironsoftware.authservice.controller;

import com.ironsoftware.authservice.dto.LoginRequest;
import com.ironsoftware.authservice.dto.RegisterRequest;
import com.ironsoftware.authservice.dto.TokenResponse;
import com.ironsoftware.authservice.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final KeycloakService keycloakService;

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(keycloakService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        keycloakService.register(request);
        return ResponseEntity.ok().build();
    }
}
