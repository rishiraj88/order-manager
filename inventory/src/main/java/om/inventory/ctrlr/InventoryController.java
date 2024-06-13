package om.inventory.ctrlr;

import lombok.RequiredArgsConstructor;
import om.inventory.svc.IInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final IInventoryService inventoryService;
    private final Logger logs = LoggerFactory.getLogger(InventoryController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantityForQuery) {
        logs.info("Retrieving inventory stock details for the product SKU: "+ skuCode);
        return inventoryService.isInStock(skuCode, quantityForQuery);
    }
}
