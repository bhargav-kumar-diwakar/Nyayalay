package com.nyayalay.service.impl;

import com.nyayalay.dto.JwtResponse;
import com.nyayalay.dto.LoginRequest;
import com.nyayalay.dto.RegisterRequest;
import com.nyayalay.entity.User;
import com.nyayalay.repository.UserRepository;
import com.nyayalay.security.JwtTokenProvider;
import com.nyayalay.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException(
                    "This email is already registered"
            );
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        return "User registered successfully with role: "+request.getRole();
    }

    @Override
    public JwtResponse login(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->
                        new RuntimeException("User not found")
                );
        return new JwtResponse(
                token,
                user.getEmail(),
                user.getRole().name()
        );
    }

}
