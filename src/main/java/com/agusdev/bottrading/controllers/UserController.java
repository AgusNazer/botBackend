package com.agusdev.bottrading.controllers;

import com.agusdev.bottrading.entity.UserEntity;
import com.agusdev.bottrading.services.UserService;
// import com.agusdev.bottrading.utils.JwtUtil;  
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager; // Para autenticar al usuario
    // private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body) {
        try {
            // Extraemos los datos del cuerpo
            String username = body.get("username");
            String email = body.get("email");
            String password = body.get("password");
            String role = body.get("role");

            // Llamamos al servicio para registrar al usuario
            UserEntity user = userService.registerUser(username, email, password, role);
            return ResponseEntity.ok("User registered successfully! ID: " + user.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> body) {
        try {
            // Extraemos los datos del cuerpo
            String username = body.get("username");
            String password = body.get("password");

            // Intentamos autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            // Si la autenticación es exitosa, guardamos el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok("Login successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error logging in: " + e.getMessage());
        }
    }

    // Luego de login redirecccionar aqui:
    @GetMapping("/private/user")
    public ResponseEntity<String> getPrivateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Obtén el nombre del usuario autenticado
        return ResponseEntity.ok("{\"message\": \"Welcome, " + username + "!\"}");
    }
}
