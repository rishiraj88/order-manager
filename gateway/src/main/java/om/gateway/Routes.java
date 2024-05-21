package om.gateway;

import org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product_msvc").route(RequestPredicates.path("/api/products"), HandlerFunctions.http("http://localhost:8080")).build();
    }
    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order_msvc").route(RequestPredicates.path("/api/orders"), HandlerFunctions.http("http://localhost:8081")).build();
    }
    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return GatewayRouterFunctions.route("inventory_msvc").route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> productDocumentationRoute() {
        return GatewayRouterFunctions.route("product-doc")
                .route(RequestPredicates.path("/gw/product/v1/doc"),HandlerFunctions.http("http://localhost:8080"))
                .filter(FilterFunctions.setPath("/doc/json"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderDocumentationRoute() {
        return GatewayRouterFunctions.route("order-doc")
                .route(RequestPredicates.path("/gw/order/v1/doc"),HandlerFunctions.http("http://localhost:8081"))
                .filter(FilterFunctions.setPath("/doc/json"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryDocumentationRoute() {
        return GatewayRouterFunctions.route("inventory-doc")
                .route(RequestPredicates.path("/gw/inventory/v1/doc"),HandlerFunctions.http("http://localhost:8082"))
                .filter(FilterFunctions.setPath("/doc/json"))
                .build();
    }
}
