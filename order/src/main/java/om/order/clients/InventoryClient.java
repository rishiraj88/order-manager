package om.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="inventory", url="${inventory.connect.url}")
public interface InventoryClient {
@GetMapping("/api/inventory")
boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantityForQuery);
}
