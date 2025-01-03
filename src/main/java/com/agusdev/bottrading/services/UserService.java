package com.agusdev.bottrading.services;

import com.agusdev.bottrading.entity.UserEntity;
import com.agusdev.bottrading.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity registerUser(String username, String email, String password, String role) {
        String encodedPassword = passwordEncoder.encode(password); // Codificar la contraseña
        UserEntity user = new UserEntity(username, email, encodedPassword);
        user.setRole(role);

        System.out.println("Usuario a registrar: " + user.getUsername() + ", " + user.getEmail() + ", " + user.getRole());  // Log para depuración

        return userRepository.save(user);
    }
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
