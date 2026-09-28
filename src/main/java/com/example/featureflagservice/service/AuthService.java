package com.example.featureflagservice.service;

import com.example.featureflagservice.dto.LoginRequest;
import com.example.featureflagservice.dto.RegisterRequest;

public interface AuthService {

    void register(
            RegisterRequest request
    );

    String login(
            LoginRequest request
    );
}