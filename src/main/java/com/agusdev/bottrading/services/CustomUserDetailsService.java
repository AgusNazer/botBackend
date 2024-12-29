package com.agusdev.bottrading.services;

import com.agusdev.bottrading.entity.UserEntity;
import com.agusdev.bottrading.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aquí manejamos el Optional correctamente
        UserEntity user = userRepository.findByUsername(username)
                                       .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
