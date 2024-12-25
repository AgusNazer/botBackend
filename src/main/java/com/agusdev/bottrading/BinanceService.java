package com.agusdev.bottrading;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import java.util.LinkedHashMap;

@Service // Marca esta clase como un servicio gestionado por Spring
public class BinanceService {

  private SpotClient client;

  // Constructor que inyecta las claves desde las propiedades
  public BinanceService(
      @Value("${binance.api-key}") String apiKey,
      @Value("${binance.secret-key}") String secretKey) {
    this.client = new SpotClientImpl(apiKey, secretKey);
  }

  // Método para obtener el precio de un par de criptomonedas (por ejemplo, BTC/USDT)
  public String getPrice(String symbol) {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    parameters.put("symbol", symbol);

    // Llama al endpoint para obtener el precio del símbolo
    String result = client.createMarket().ticker(parameters);
    return result;
  }
}
