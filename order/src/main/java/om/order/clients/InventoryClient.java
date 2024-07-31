package om.order.clients;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * to replicate the API of Inventory microservice
 */
@Slf4j
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

@GetExchange("/api/inventory")
@CircuitBreaker(name="inventory",fallbackMethod = "fallbackMethod")
@Retry(name="inventory")
boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantityForQuery);

default boolean fallbackMethod(String skuCode, Integer quantityForQuery, Throwable throwable) {
    log.info("Sufficient quantity of SKU {} not found in inventory. Reason: {}",skuCode,throwable.getMessage());
    return false;
}
}
