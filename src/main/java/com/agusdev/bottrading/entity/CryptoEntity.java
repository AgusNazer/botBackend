package com.agusdev.bottrading.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cryptos")  // Nombre de la tabla, puedes cambiarlo si lo prefieres
public class CryptoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;  // El símbolo de la criptomoneda, como "BTCUSDT"
    private String name;    // El nombre de la criptomoneda, como "Bitcoin"
    
    private BigDecimal price;  // El precio de la criptomoneda
    
    @Column(name = "timestamp")
    private LocalDateTime timestamp;  // El momento en el que se registró el precio

    // Constructor sin parámetros (necesario para Hibernate)
    public CryptoEntity() {
    }

    // Constructor con parámetros
    public CryptoEntity(String symbol, String name, BigDecimal price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.timestamp = LocalDateTime.now();  // Establecer la fecha de creación al momento de la creación
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
