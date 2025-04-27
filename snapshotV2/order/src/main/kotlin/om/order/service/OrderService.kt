package om.order.service

import om.order.dto.OrderReq
import om.order.dto.OrderResp

interface OrderService {
    fun getAllOrders():Iterable<OrderResp>
    fun createOrder(orderReq: OrderReq):OrderResp
}