package com.agusdev.bottrading.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agusdev.bottrading.entity.BotEntity;
import com.agusdev.bottrading.repositories.BotRepository;

@Service
public class BotService {
    
    @Autowired
    private BotRepository botRepository;

    // Obtener todos los bots
    public List<BotEntity> getAllBots() {
        return botRepository.findAll();
    }

    // Obtener un bot por ID
    public Optional<BotEntity> getBotById(Long id) {
        return botRepository.findById(id);
    }

    // Crear un nuevo bot
    public BotEntity createBot(BotEntity bot) {
        return botRepository.save(bot);
    }

    // Actualizar un bot existente
    public BotEntity updateBot(Long id, BotEntity botDetails) {
        if (botRepository.existsById(id)) {
            botDetails.setId(id);
            return botRepository.save(botDetails);
        }
        return null;
    }

    // Eliminar un bot
    public boolean deleteBot(Long id) {
        if (botRepository.existsById(id)) {
            botRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
