package fr.formationacademy.scpiinvestplusnotification.configuration;

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
                        .title("SCPI Invest Plus - Notification API")
                        .description("API documentation for managing notifications in SCPI Invest Plus."));
    }
}

