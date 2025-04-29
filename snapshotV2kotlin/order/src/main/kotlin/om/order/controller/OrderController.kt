package om.order.controller

import om.order.dto.OrderReq
import om.order.dto.OrderResp
import om.order.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/orders")
class OrderController(val orderService: OrderService) {

    @GetMapping
    fun getAllOrders(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(required = false) sortBy: String?
    ): ResponseEntity<Iterable<OrderResp>> {
        return if (null == sortBy) ResponseEntity.ok(orderService.getAllOrders(page, size))
                else ResponseEntity.ok(orderService.getAllOrders(sortBy))
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody orderReq: OrderReq):OrderResp {
    return orderService.createOrder(orderReq);
    }

    @GetMapping("{id}")
    fun getProduct(@PathVariable id: String): ResponseEntity<OrderResp> {
        val order = orderService.getOrder(id)
        return if (null == order) ResponseEntity.notFound().build() else ResponseEntity.ok(order)
    }
}

