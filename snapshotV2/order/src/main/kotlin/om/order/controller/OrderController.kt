package om.order.controller

import om.order.dto.OrderReq
import om.order.dto.OrderResp
import om.order.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/orders")
class OrderController(val orderService: OrderService) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllOrders():Iterable<OrderResp> {
        return orderService.getAllOrders()
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody orderReq: OrderReq):OrderResp {
    return orderService.createOrder(orderReq);
    }
}

