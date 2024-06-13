package om.inventory.svc;

import lombok.RequiredArgsConstructor;
import om.inventory.dao.InventoryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements IInventoryService {
    private final InventoryRepo inventoryRepo;
    private final Logger logs = LoggerFactory.getLogger(InventoryServiceImpl.class);

    public boolean isInStock(String itemSkuCode, Integer quantityToCheck) {
        if (quantityToCheck > 0) {
            boolean available = inventoryRepo.existsByItemSkuCodeAndQuantityInStockIsGreaterThanEqual(itemSkuCode, quantityToCheck);
            if(available)
                logs.info("SUCCESS: The requested quantity of " + itemSkuCode + " is available for preparing an order.");
            else
                logs.info("FAILURE: The product SKU " + itemSkuCode + " is out of stock.");
            return available;
        }
        logs.info("ERROR: Kindly query with a decent quantity. Non-positive quantities are not good for checking the stock availability.");
        return false;

    }
}
