package com.example.featureflagservice.service.impl;

import com.example.featureflagservice.dto.LoginRequest;
import com.example.featureflagservice.dto.RegisterRequest;
import com.example.featureflagservice.entity.User;
import com.example.featureflagservice.enums.RoleType;
import com.example.featureflagservice.repository.UserRepository;
import com.example.featureflagservice.security.JwtUtil;
import com.example.featureflagservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(RoleType.USER)
                .build();

        userRepository.save(user);
    }

    @Override
    public String login(LoginRequest request) {
        User user =
                userRepository.findByUsername(
                                request.getUsername())
                        .orElseThrow();

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid Credentials");
        }

        return jwtUtil.generateToken(
                user.getUsername());
    }
}
