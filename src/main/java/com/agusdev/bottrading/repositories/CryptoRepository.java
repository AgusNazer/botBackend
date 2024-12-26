package com.agusdev.bottrading.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agusdev.bottrading.entity.CryptoEntity;

public interface CryptoRepository extends JpaRepository<CryptoEntity, Long> {
     // Puedes agregar consultas personalizadas, si es necesario
    Optional<CryptoEntity> findBySymbol(String symbol);  // Ejemplo de consulta personalizada

}
