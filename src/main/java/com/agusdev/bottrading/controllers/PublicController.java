package com.agusdev.bottrading.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/public") // Prefijo ajustado para indicar que estas rutas son públicas
@RequiredArgsConstructor
public class PublicController {

    @GetMapping("/home")
    public String home() {
        return "Public home: accesible sin autenticación";
    }

    @GetMapping("/about")
    public String about() {
        return "About: información pública sobre la aplicación";
    }
}
