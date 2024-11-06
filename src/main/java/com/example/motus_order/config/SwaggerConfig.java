package com.example.motus_order.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Consumidora - Order Management")
                        .version("v1.0")
                        .description("Documentação da API Consumidora para gerenciamento de pedidos com Kafka e MongoDB"));
    }
}