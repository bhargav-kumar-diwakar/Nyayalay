package com.nyayalay.service;

import com.nyayalay.dto.JwtResponse;
import com.nyayalay.dto.LoginRequest;
import com.nyayalay.dto.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest registerRequest);
    JwtResponse login(LoginRequest loginRequest);
}
