package com.agusdev.bottrading.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.binance.connector.client.SpotClient;

import com.binance.connector.client.impl.SpotClientImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
// import java.util.stream.Collectors;


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

  // Método para obtener las primeras 100 criptomonedas
  public String getExchangeInfo() {
    LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
    // Llama al endpoint para obtener la información de los mercados
    return client.createMarket().exchangeInfo(parameters);
}
public List<String> getTop100Cryptos(String result) {
    List<String> cryptos = new ArrayList<>();
    JSONObject jsonResponse = new JSONObject(result);
    
    // Extraer el array de 'symbols' de la respuesta
    JSONArray symbols = jsonResponse.getJSONArray("symbols");
    
    // Filtrar los primeros 100 pares de criptomonedas
    for (int i = 0; i < 100 && i < symbols.length(); i++) {
        JSONObject symbolInfo = symbols.getJSONObject(i);
        cryptos.add(symbolInfo.getString("symbol"));
    }
    
    return cryptos;
}
  
}
