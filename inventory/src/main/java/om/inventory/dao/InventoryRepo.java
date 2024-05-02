package om.inventory.dao;

import om.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {
    boolean existsByItemSkuCodeAndQuantityInStockIsGreaterThanEqual(String itemSkuCode, Integer quantityToCheck);
}
