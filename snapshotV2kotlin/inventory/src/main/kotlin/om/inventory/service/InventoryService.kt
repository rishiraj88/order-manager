package om.inventory.service

interface InventoryService {
    fun isItemInStock(skuCode: String, quantityToCheck: Int):Boolean
}
