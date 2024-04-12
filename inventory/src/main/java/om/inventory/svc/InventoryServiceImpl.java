package om.inventory.svc;

import lombok.RequiredArgsConstructor;
import om.inventory.dao.InventoryRepo;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements IInventoryService {

    private final InventoryRepo inventoryRepo;
    public boolean isInStock(String itemSkuCode,Integer quantityToCheck) {
        if(quantityToCheck>0)
            return inventoryRepo.existsByItemSkuCodeAndQuantityInStockIsGreaterThanEqual(itemSkuCode,quantityToCheck);

        return false;

    }
}
