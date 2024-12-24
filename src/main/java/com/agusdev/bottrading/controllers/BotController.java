package com.agusdev.bottrading.controllers;

import com.agusdev.bottrading.entity.BotEntity;
import com.agusdev.bottrading.services.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bots")
public class BotController {

    @Autowired
    private BotService botService;

    // Obtener todos los bots
    @GetMapping
    public List<BotEntity> getAllBots() {
        return botService.getAllBots();
    }

    // Obtener un bot por ID
    @GetMapping("/{id}")
    public ResponseEntity<BotEntity> getBotById(@PathVariable Long id) {
        Optional<BotEntity> bot = botService.getBotById(id);
        return bot.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo bot
    @PostMapping
    public ResponseEntity<BotEntity> createBot(@RequestBody BotEntity bot) {
        BotEntity createdBot = botService.createBot(bot);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBot);
    }

    // Actualizar un bot existente
    @PutMapping("/{id}")
    public ResponseEntity<BotEntity> updateBot(@PathVariable Long id, @RequestBody BotEntity botDetails) {
        BotEntity updatedBot = botService.updateBot(id, botDetails);
        return updatedBot != null ? ResponseEntity.ok(updatedBot) : ResponseEntity.notFound().build();
    }

    // Eliminar un bot
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBot(@PathVariable Long id) {
        return botService.deleteBot(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
