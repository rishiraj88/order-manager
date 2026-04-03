package om.order.service

import om.order.dto.OrderReq
import om.order.dto.OrderResp
import om.order.entity.Order
import om.order.exception.ResourceNotFoundException
import om.order.repo.OrderRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class OrderServiceImpl(val orderRepository: OrderRepository) : OrderService {

    override fun getAllOrders(page: Int, size: Int): Iterable<OrderResp> {
        val pageable: Pageable = PageRequest.of(page, size)
        val orders = orderRepository.findAll(pageable).map { it.toResp() }
        if (orders.isEmpty) throw ResourceNotFoundException("No orders found.")
        return orders
    }

    override fun getAllOrders(sortBy: String?): Iterable<OrderResp> {
        val sortedOrders = if (null == sortBy) orderRepository.findAll().map { it.toResp() } else orderRepository.findAll(
            Sort.by(
                Sort.Direction.DESC, sortBy
            )
        ).map { it.toResp() }
        if (sortedOrders.isEmpty()) throw ResourceNotFoundException("No orders available to sort and show.")
        return sortedOrders
    }

    override fun createOrder(orderReq: OrderReq): OrderResp {
        // TODO https://github.com/rishiraj88/order-manager/issues/71
        // Invoke Delivery and Billing features when a new order is created. #71
        return orderRepository.save(Order.fromReq(orderReq)).toResp()
    }

    override fun getOrder(id: String): OrderResp? {
        val matchingOrder = orderRepository.findById(id).getOrNull()?.toResp()
        if (null == matchingOrder) throw ResourceNotFoundException("No matching orders found.")
        return matchingOrder
    }
}