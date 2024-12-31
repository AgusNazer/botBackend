package com.agusdev.bottrading.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails{

    private UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // roles del usuario
       
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole())
        );
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }
    
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true; // Cambia según la lógica de tu aplicación
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true; // Cambia según la lógica de tu aplicación
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Cambia según la lógica de tu aplicación
    }
    
    @Override
    public boolean isEnabled() {
        return true; // Cambia según la lógica de tu aplicación
    }

}
