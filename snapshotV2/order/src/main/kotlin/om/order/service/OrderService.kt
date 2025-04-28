package om.order.service

import om.order.dto.OrderReq
import om.order.dto.OrderResp

interface OrderService {
    fun getAllOrders(page: Int, size: Int):Iterable<OrderResp>
    fun getAllOrders(sortBy: String?):Iterable<OrderResp>
    fun createOrder(orderReq: OrderReq):OrderResp
    fun getOrder(id: String): OrderResp?
}