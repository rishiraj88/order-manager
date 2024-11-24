package om.order.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * to replicate the API of Inventory microservice
 */
@Slf4j
public abstract class InventoryClient {
    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    public abstract boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantityForQuery);

     boolean fallbackMethod(String skuCode, Integer quantityForQuery, Throwable throwable) {
        log.info("Sufficient quantity of SKU {} not found in inventory. Reason: {}", skuCode, throwable.getMessage());
        return false;
    }
}
