package com.amazecare.service.impl;

import com.amazecare.dto.LoginRequest;
import com.amazecare.dto.RegisterRequest;
import com.amazecare.entity.User;
import com.amazecare.repository.UserRepository;
import com.amazecare.security.JwtUtil;
import com.amazecare.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole().toUpperCase());
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }

    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        User user = userRepository.findByUsername(request.getUsername()).get();
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok().body(
            java.util.Map.of("token", token, "role", user.getRole(), "username", user.getUsername())
        );
    }
}
