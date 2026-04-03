package om.inventory.repo

import om.inventory.entity.InventoryItem
import org.springframework.data.jpa.repository.JpaRepository

interface InventoryRepository: JpaRepository<InventoryItem,String> {
    fun existsBySkuCodeAndQuantityInStockIsGreaterThanEqual(skuCode: String, quantityToCheck: Int): Boolean

}
