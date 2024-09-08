package com.vass.inditex.boot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info =
        @Info(
            title = "Inditex ecomvass artifact",
            description = "Inditex ecomvass API",
            version = "v1"))
@Configuration
public class OpenApiConfiguration {}
