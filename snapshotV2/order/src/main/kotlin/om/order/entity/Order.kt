package om.order.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import om.order.dto.OrderReq
import om.order.dto.OrderResp
import org.hibernate.annotations.UuidGenerator

@Entity @Table(name = "orders")
data class Order(val orderNumber: String,
                 val skuCode: String,
                 val itemRate: Float,
                 val itemQuantity: Int) {

    @Id @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: String

    fun toResp() = OrderResp(id, orderNumber,
         skuCode,
     itemRate,
     itemQuantity)

    companion object {
        fun fromReq(orderReq: OrderReq): Order =
            Order(orderReq.orderNumber,orderReq.skuCode,orderReq.itemRate, orderReq.itemQuantity)
    }
}