package com.example._app.Configuration;

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
                        .title("API Gestión Equipo de Fútbol")
                        .version("1.0")
                        .description("API REST para gestionar jugadores, entrenadores, partidos y estadísticas"));
    }
}