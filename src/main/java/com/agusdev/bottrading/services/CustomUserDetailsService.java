package com.agusdev.bottrading.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agusdev.bottrading.entity.SecurityUser;
import com.agusdev.bottrading.entity.UserEntity;
import com.agusdev.bottrading.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
            
                if (user == null) {
                    throw new UsernameNotFoundException("Usuario no encontrado");
                }
        return new SecurityUser(user);
    }
}
