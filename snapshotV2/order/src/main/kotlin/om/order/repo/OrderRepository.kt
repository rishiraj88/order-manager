package om.product.repo

import om.order.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, String> {
    fun findBySkuCodeIgnoreCase(skuCode: String?): List<Order?>?

    // Enabling ignoring case
    fun findByorderNumberIgnoreCase(desc: String?): List<Order?>?

    // Enabling static ORDER BY
    fun findBySkuCodeOrderBySkuCodeAsc(skuCode: String?): List<Order?>?
    fun findBySkuCodeOrderBySkuCodeDesc(skuCode: String?): List<Order?>?
}