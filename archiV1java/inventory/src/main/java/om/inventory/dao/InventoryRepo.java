package om.inventory.dao;

import om.inventory.service.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryItem,Long> {
    boolean existsByItemSkuCodeAndQuantityInStockIsGreaterThanEqual(String itemSkuCode, Integer quantityToCheck);
}
