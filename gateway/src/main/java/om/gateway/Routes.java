package om.gateway;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product_msvc")
                .route(RequestPredicates.path("/api/products"), HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productMsvcCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order_msvc").route(RequestPredicates.path("/api/orders"), HandlerFunctions.http("http://localhost:8081"))    .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderMsvcCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return GatewayRouterFunctions.route("inventory_msvc").route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))    .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryMsvcCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productDocumentationRoute() {
        return GatewayRouterFunctions.route("product-doc")
                .route(RequestPredicates.path("/gw/product/v1/doc"),HandlerFunctions.http("http://localhost:8080"))    .filter(CircuitBreakerFilterFunctions.circuitBreaker("productDocCircuitBreaker", URI.create("forward:/fallbackRoute")))

                .filter(FilterFunctions.setPath("/doc/json"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderDocumentationRoute() {
        return GatewayRouterFunctions.route("order-doc")
                .route(RequestPredicates.path("/gw/order/v1/doc"),HandlerFunctions.http("http://localhost:8081"))    .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderDocCircuitBreaker", URI.create("forward:/fallbackRoute")))

                .filter(FilterFunctions.setPath("/doc/json"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryDocumentationRoute() {
        return GatewayRouterFunctions.route("inventory-doc")
                .route(RequestPredicates.path("/gw/inventory/v1/doc"),HandlerFunctions.http("http://localhost:8082"))    .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryDocCircuitBreaker", URI.create("forward:/fallbackRoute")))

                .filter(FilterFunctions.setPath("/doc/json"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> fallbackRoute(){
        return GatewayRouterFunctions.route("fallbackRoute")
                .GET("fallbackRoute",request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service unavailable right now. Try again later.")).build();

    }
}
