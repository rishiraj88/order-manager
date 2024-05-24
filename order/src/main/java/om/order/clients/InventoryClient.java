package om.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {
@GetExchange("/api/inventory")
boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantityForQuery);
}
