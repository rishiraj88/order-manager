package om.order.repo

import om.order.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, String> {
    fun findBySkuCodeIgnoreCase(skuCode: String?): List<Order?>?
    fun findByOrderNumberIgnoreCase(desc: String?): List<Order?>?
    fun findBySkuCodeOrderBySkuCodeAsc(skuCode: String?): List<Order?>?
    fun findBySkuCodeOrderBySkuCodeDesc(skuCode: String?): List<Order?>?
}