package com.digio.challenge.infrastructure.config;

import com.digio.challenge.infrastructure.exceptionhandler.ErrorResponse;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api DIGIO Challenge")
                        .version("1.0.0")
                        .description("Microservice REST API with specific endpoints that return the " +
                                "analyze customers' wine purchases, providing detailed information about purchases, " +
                                "performs annual analyses, and identifies the most loyal customers.")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")
                        )
                ).components(new Components()
                        .schemas(gerarSchemas()));
    }


    private Map<String, Schema> gerarSchemas() {
        var problemSchema = ModelConverters.getInstance().read(ErrorResponse.class);
        return new HashMap<>(problemSchema);
    }

}
