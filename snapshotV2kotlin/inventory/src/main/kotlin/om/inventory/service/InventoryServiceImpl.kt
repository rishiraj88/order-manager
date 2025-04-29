package om.inventory.service

import om.inventory.exception.StockLevelException
import om.inventory.repo.InventoryRepository
import org.springframework.stereotype.Service

@Service
class InventoryServiceImpl(val inventoryRepository: InventoryRepository):InventoryService {
    override fun isItemInStock(skuCode: String, quantityToCheck: Int):Boolean {
        if( quantityToCheck>0){
            return inventoryRepository.existsBySkuCodeAndQuantityInStockIsGreaterThanEqual(skuCode,quantityToCheck)
        }
        throw StockLevelException("The requested quantity of the item $skuCode is not available at this moment. Please try ordering a smaller quantity or only other items.")
    }

}
