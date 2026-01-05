package om.order.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Documentation of the API of Order module (microservice) using OpenAPI Specification (OAS)
 */
@Configuration
public class OpenApiSpecsConfig {
    @Bean
    public OpenAPI OrderOasApi() {
        return new OpenAPI().info(new Info().title("Order API documentation for Order Manager").description("""
                The application Order Manager has three main microservices, exposing their interfaces as RESTful APIs:
                                
                > Product
                                
                > Order
                                
                > Inventory

                Order microservice is for creating, listing, modifying and removing orders and order details. Its API has the base URI as "/api/v1/orders".
                """).version("v1.0.0").contact(new Contact().name("Rishi Raj").url("https://www.linkedin.com/in/rishirajopenminds/")));
    }
}
