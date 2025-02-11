package om.order.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * to replicate the API of Inventory microservice
 */
public interface InventoryClient {
    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    public abstract boolean isItemInStock(@RequestParam String skuCode, @RequestParam Integer quantityForQuery);

    default boolean fallbackMethod(String skuCode, Integer quantityForQuery, Throwable throwable) {
        return false;
    }
}
