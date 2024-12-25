package com.agusdev.bottrading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BottradingApplication {

	public static void main(String[] args) {
		// SpringApplication.run(BottradingApplication.class, args); // esta es la linea predeterminada por spring para el arranque
		 // Ejecuta la aplicación Spring Boot y obtiene el contexto
		 ConfigurableApplicationContext context = SpringApplication.run(BottradingApplication.class, args);

		// Instancia del servicio con las claves API
		//  String apiKey = "USBdYlrWJC3bmjI0ELNRagQegByX5Exe7w817ytoaT0poMsAzblaIYl70aK9ry9n";
		//  String secretKey = "U5hBLlOR7O3dwRv5bHIsjTSldnUyEz1dPWkGgfQbSxc5W4rQIOedgmXOd6pUiiol";
		 BinanceService binanceService = context.getBean(BinanceService.class);
 
		 // Prueba del método getPrice
		 String price = binanceService.getPrice("BTCUSDT");
		 System.out.println("Precio de BTC/USDT: " + price);
	}

}
