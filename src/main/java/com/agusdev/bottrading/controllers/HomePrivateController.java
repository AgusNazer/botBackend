package com.agusdev.bottrading.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/private") // Cambiamos el prefijo a /v1/private para diferenciar claramente las rutas protegidas
@RequiredArgsConstructor
public class HomePrivateController {

    @GetMapping("/home")
    public String privateHome() {
        return "Private home: solo para usuarios autenticados";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')") // Asegúrate de que el rol en la base de datos tenga el prefijo ROLE_
    public String admin() {
        return "Admin: contenido exclusivo para administradores";
    }
}
