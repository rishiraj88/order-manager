package om.inventory.dao;

import om.inventory.InventoryApplication;
import om.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepo extends JpaRepository<Inventory,Long> {
    boolean existsByItemSkuCodeAndQuantityInStockIsGreaterThanEqual(String itemSkuCode, Integer quantityToCheck);
}
