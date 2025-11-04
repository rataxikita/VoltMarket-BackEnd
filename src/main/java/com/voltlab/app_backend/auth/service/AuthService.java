package com.voltlab.app_backend.auth.service;

import com.voltlab.app_backend.auth.dto.*;
import com.voltlab.app_backend.auth.JwtUtil;
import com.voltlab.app_backend.model.User;
import com.voltlab.app_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; //utilidad para generar y validar tokens JWT
    
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        
        if (userRepository.existsByemail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNombre(request.getNombre());
        user.setApellido(request.getApellido());
        
        User savedUser = userRepository.save(user);
        
        String token = jwtUtil.generateToken(
            savedUser.getEmail(),
            savedUser.getId(),
            savedUser.getRole().name()
        );
        
        return new AuthResponse(
                token, 
                "Bearer",
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getNombre(),
                savedUser.getApellido(),
                savedUser.getRole().name()
        );
    }
    
    public AuthResponse login(LoginRequest request) {
        
        User user = userRepository.findByemail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        
        String token = jwtUtil.generateToken(
            user.getEmail(),
            user.getId(),
            user.getRole().name()
        );
        
        return new AuthResponse(
                token, 
                "Bearer",
                user.getId(),
                user.getEmail(),
                user.getNombre(),
                user.getApellido(),
                user.getRole().name()
        );
    }
    
    @Transactional
    public void updateProfile(Long userId, UpdateProfileRequest request) {
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (request.getNombre() != null && !request.getNombre().isEmpty()) {
            user.setNombre(request.getNombre());
        }
        
        if (request.getApellido() != null && !request.getApellido().isEmpty()) {
            user.setApellido(request.getApellido());
        }
        
        userRepository.save(user);
    }
    
}
