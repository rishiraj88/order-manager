package om.order;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSpecsConfig {
    @Bean
    public OpenAPI OrderOasApi() {
        return new OpenAPI().info(new Info().title("Order API documentation for Order Manager").description("""
                This application has three main components, exposing their services with RESTful Web Services:
                                
                > Product
                                
                > Order
                                
                > Inventory

                Order module is for creating, listing, modifying and removing orders and order details. Its API has its base URI as "/api/orders".
                """).version("v1.0.0").contact(new Contact().name("Rishi Raj").url("https://bio.link/rishiraj49de")));
    }
}
