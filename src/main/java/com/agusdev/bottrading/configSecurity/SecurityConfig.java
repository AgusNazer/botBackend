package com.agusdev.bottrading.configSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import com.agusdev.bottrading.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/v1/public/**").permitAll()
                        .requestMatchers("/api/users/register").permitAll()  // Permitir registro sin autenticación
                        .requestMatchers("/api/users/create-admin").permitAll()  // Permitir registro sin autenticación
                        .requestMatchers("/v1/private/**").authenticated() // Requiere autenticación para rutas privadas
                        // Ruta para usuarios regulares
                        .requestMatchers("/v1/private/user").hasAuthority("USER")
                        .requestMatchers("/v1/private/admin").hasAuthority("ADMIN")  // Requiere el rol ADMIN
                        .requestMatchers("/api/users/all").hasAuthority("ADMIN") // Solo ADMIN puede acceder
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                            // Devolver respuesta JSON indicando inicio de sesión exitoso
                            httpServletResponse.setContentType("application/json");
                            httpServletResponse.getWriter().write("{\"message\": \"Login successful!\"}");
                            httpServletResponse.sendRedirect("/api/users/private/user");
                        })) // Custom success handler to return JSON on successful login
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Exponer el AuthenticationManager como un bean
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService)  // Usamos el UserDetailsService
                .passwordEncoder(passwordEncoder());  // Usamos el PasswordEncoder
        return authenticationManagerBuilder.build();
    }
}
