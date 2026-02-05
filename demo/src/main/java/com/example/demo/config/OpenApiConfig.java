package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Cadastro de Produtos")
                        .description("API REST para cadastro, consulta, atualização e remoção de produtos desenvolvida como exercício do BootcampDeloitte")
                        .version("1.0.0"));
    }
}