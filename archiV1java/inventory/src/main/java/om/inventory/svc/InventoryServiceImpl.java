package om.inventory.svc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.inventory.dao.InventoryRepo;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service @Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepo inventoryRepo;

    public boolean isItemInStock(String itemSkuCode, Integer quantityToCheck) {
        if (quantityToCheck > 0) {
            boolean available = inventoryRepo.existsByItemSkuCodeAndQuantityInStockIsGreaterThanEqual(itemSkuCode, quantityToCheck);
            if(available)
                log.info("SUCCESS: The requested quantity of the item (SKU: " + itemSkuCode + ") is available for preparing the requested order.");
            else
                log.info("FAILURE: The product item with SKU " + itemSkuCode + " is out of stock.");
            return available;
        }
        log.info("ERROR: Kindly place the next request with a decent quantity. Non-positive quantities are not acceptable at this moment.");
        return false;
    }
}
