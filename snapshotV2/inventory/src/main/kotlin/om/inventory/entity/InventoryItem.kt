package om.inventory.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import om.inventory.dto.InventoryItemReq
import om.inventory.dto.InventoryItemResp
import org.hibernate.annotations.UuidGenerator

@Entity @Table(name = "inventory_items")
data class InventoryItem(val skuCode: String,
                         val quantityInStock: Int) {

    @Id @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: String

    fun toResp() = InventoryItemResp(id, skuCode,quantityInStock)

    companion object {
        fun fromReq(itemReq: InventoryItemReq): InventoryItem =
            InventoryItem(itemReq.skuCode,itemReq.quantityToCheck)
    }
}