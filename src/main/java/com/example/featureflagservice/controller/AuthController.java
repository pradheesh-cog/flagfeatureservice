package com.example.featureflagservice.controller;

import com.example.featureflagservice.dto.JwtResponse;
import com.example.featureflagservice.dto.LoginRequest;
import com.example.featureflagservice.dto.RegisterRequest;
import com.example.featureflagservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return "User Registered";
    }

    @PostMapping("/login")
    public JwtResponse login(
            @RequestBody LoginRequest request) {

        return new JwtResponse(
                authService.login(request)
        );
    }
}