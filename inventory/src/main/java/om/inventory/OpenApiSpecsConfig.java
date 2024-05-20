package om.inventory;

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
                This application has three main components, exposing their services with RESTful Web Services:
                                
                > Product
                                
                > Order
                                
                > Inventory

                Inventory module is for checking whether there is enough quantity available in inventory stock in order to place a new item order. Its API has its base URI as "/api/inventory".
                """).version("v1.0.0").contact(new Contact().name("Rishi Raj").url("https://bio.link/rishiraj49de")));
    }
}
