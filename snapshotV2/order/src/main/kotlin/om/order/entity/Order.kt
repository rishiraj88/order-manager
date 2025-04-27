package om.order.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import om.order.dto.OrderReq
import om.order.dto.OrderResp
import java.util.UUID

@Entity
data class Order(val orderNumber: String,
                 val skuCode: String,
                 val itemRate: Number,
                 val itemQuantity: Number) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    fun toResp() = OrderResp(id, orderNumber,
         skuCode,
     itemRate,
     itemQuantity)

    companion object {
        fun fromReq(orderReq: OrderReq): Order =
            Order(orderReq.orderNumber,orderReq.skuCode,orderReq.itemRate, orderReq.itemQuantity)
    }
}