package dev.jombi.diverse.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun api(): OpenAPI = OpenAPI()
        .info(
            Info()
                .title("Diverse")
                .description("Diverse API Documentation")
                .version("v1.0")
                .contact(
                    Contact()
                        .name("P+")
                        .url("https://pplus.com")
                        .email("contact@pplus.com")
                )
                .license(
                    License()
                        .name("MIT")
                        .url("https://pplus.com/license")
                )
                .termsOfService("https://pplus.com/terms")
        )
        .addSecurityItem(SecurityRequirement().addList("Authorization"))
        .components(
            Components()
                .addSecuritySchemes(
                    "Authorization", SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("Authorization")
                        .`in`(SecurityScheme.In.HEADER)
                        .name("Authorization")
                )
        )
}