package om.order.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * to replicate the API signature of Inventory module
 * @GetExchange("/api/v1/inventory") from Spring 6 is used here, which is an alias to @HttpExchange(method = "GET")
 */
public interface InventoryClient {
    @GetExchange("/api/v1/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod") //The name "inventory matches the circuit breaker name in application.properties"
    @Retry(name = "inventory")
    public abstract boolean isItemInStock(@RequestParam String skuCode, @RequestParam Integer quantityForQuery);

    default boolean fallbackMethod(String skuCode, Integer quantityForQuery, Throwable throwable) {
        return false;
    }
}
