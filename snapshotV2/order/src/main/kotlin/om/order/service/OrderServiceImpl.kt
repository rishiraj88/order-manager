package om.order.service

import om.order.dto.OrderReq
import om.order.dto.OrderResp
import om.order.entity.Order
import om.order.repo.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(val orderRepository: OrderRepository): OrderService {

    override fun getAllOrders(): Iterable<OrderResp> {
        return orderRepository.findAll().map { it.toResp() }
    }

    override fun createOrder(orderReq: OrderReq):OrderResp {
        return orderRepository.save(Order.fromReq(orderReq)).toResp()
    }
}