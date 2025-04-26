package om.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSpecsConfig {
    @Bean
    public OpenAPI productOasApi() {
        return new OpenAPI().info(new Info().title("Product API documentation for Order Manager").description("""
                This application has three main microservices, exposing their interfaces as RESTful APIs:
                                
                > Product
                                
                > Order
                                
                > Inventory

                Product microservice is for adding, listing, modifying and removing products. Its API has the base URI: "/api/v1/products".
                """).version("v1.0.0").contact(new Contact().name("Rishi Raj").url("https://bio.link/rishiraj49de")));
    }
}
