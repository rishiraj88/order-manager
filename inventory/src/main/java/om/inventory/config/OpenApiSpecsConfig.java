package om.inventory.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSpecsConfig {
    @Bean
    public OpenAPI InventoryOasApi() {
        return new OpenAPI().info(new Info().title("Inventory API documentation for Order Manager").description("""
                This application has three main microservices, exposing their interfaces as RESTful APIs:
                                
                > Product
                                
                > Order
                                
                > Inventory

                Inventory microservice is for checking whether there is enough quantity of specified items available in inventory stock so as to fulfill a new order. Its API has the base URI as "/api/v1/inventory".
                """).version("v1.0.1").contact(new Contact().name("Rishi Raj").url("https://bio.link/rishiraj49de")));
    }
}
