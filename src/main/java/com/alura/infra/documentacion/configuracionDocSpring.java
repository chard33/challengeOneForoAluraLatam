package com.alura.infra.documentacion;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configuracionDocSpring {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components().
                                addSecuritySchemes(
                                        "bearer-key",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .info(new Info()
                              .title("API Alura-foro")
                              .description("API Rest de la aplicación Alura-foro, que " +
                                                   "contiene las funcionalidades de CRUD.")
                              .contact(new Contact()
                                               .name(": Carlos Miranda")
                                               .email("https://www.linkedin.com/in/juan-carlos-perez-miranda-268710201/")));
    }
}
