// CONFIGURAR CONECTAR LA API DE BINANCE PARA UTILIZAR LOS ENDPOINTS PARA TRAER MUCHAS CRYPTOS
// SIN GUARDARLAS EN LA BASE DE DATOS DE MI APP   
package com.agusdev.bottrading.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BinanceApiConfig {

    @Value("${binance.api.base-url}")
    private String baseUrl;

        // Método para obtener la URL base
    public String getBaseUrl() {
        return baseUrl;
    }

    // Bean para RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
