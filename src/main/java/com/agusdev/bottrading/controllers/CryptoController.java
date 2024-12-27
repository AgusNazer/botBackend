package com.agusdev.bottrading.controllers;

import com.agusdev.bottrading.entity.CryptoEntity;
import com.agusdev.bottrading.services.BinanceService;
import com.agusdev.bottrading.services.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/crypto")

public class CryptoController {
     //////////BINANCE PRICES
    @Autowired
    private BinanceService binanceService;
    // Obtener el precio de una criptomoneda (Ejemplo: BTCUSDT)
    @GetMapping("/data")
    public ResponseEntity<String> getCryptoPrice(@RequestParam String symbol) {
        try {
            String price = binanceService.getPrice(symbol);
            return ResponseEntity.ok(price); // Devuelve el precio en la respuesta
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error obteniendo el precio");
        }
    }
    //swagger DOC
    

    @Autowired
    private CryptoService cryptoService;
    @GetMapping("/cryptos")
    public List<CryptoEntity> getAllCryptos() {
        return cryptoService.getAllCryptos();
    }
    @Autowired
    @GetMapping("/top-100")
    public ResponseEntity<List<String>> getTop100Cryptos(){
        try {
            // Llama al servicio para obtener la información de los primeros 100 pares
            String result = binanceService.getExchangeInfo();
            List<String> top100Cryptos = binanceService.getTop100Cryptos(result);
            return ResponseEntity.ok(top100Cryptos); // Devuelve las primeras 100 criptomonedas
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}



