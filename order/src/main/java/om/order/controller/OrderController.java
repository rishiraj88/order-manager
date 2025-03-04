package om.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.order.config.Constants;
import om.order.dto.OrderReq;
import om.order.dto.OrderResp;
import om.order.service.IOrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController @Slf4j
//@RequestMapping(value = "/api/orders",headers = "Accept-Version=v1") // A different scheme of API versioning
@RequestMapping(value = "/api/v1/orders")
public class OrderController {
    private final IOrderService orderService;

    // modern way to write Controller Endpoints. Older way is depicted in ResponseEntity<List<ProductResp>> ProductController.getAllProducts()
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public String createOrder(@Valid @RequestBody OrderReq orderReq) {
        orderService.createOrder(orderReq);
        log.debug(Constants.NEW_ORDER_PLACED_MSG);
        return Constants.NEW_ORDER_PLACED_MSG;
    }

    // getOrders()
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResp> getOrders(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderService.getOrders(pageable);
    }

    //TODO add getOrderByOrderNumber()

    //TODO add getOrdersByUserEmail()

    //TODO add getOrdersByUserName()
}
