package fatiha.elhabti.fatihaelhabti.web;

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
                        .title("Banking Credit Management API")
                        .version("1.0")
                        .description("API for managing banking credits, clients, and reimbursements"));
    }
}