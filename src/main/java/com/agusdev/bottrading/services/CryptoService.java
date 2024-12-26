package com.agusdev.bottrading.services;

import com.agusdev.bottrading.entity.CryptoEntity;
import com.agusdev.bottrading.repositories.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    @Autowired
    private CryptoRepository cryptoRepository;

    // Método para obtener todas las criptomonedas
    public List<CryptoEntity> getAllCryptos() {
        return cryptoRepository.findAll();  // Utiliza el método findAll de JpaRepository
    }

    // Otros métodos como crear, actualizar, eliminar criptos podrían ir aquí...
}
