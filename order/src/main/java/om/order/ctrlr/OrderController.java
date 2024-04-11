package om.order.ctrlr;

import lombok.RequiredArgsConstructor;
import om.order.dto.OrderReq;
import om.order.svc.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final IOrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderReq orderReq) {
        orderService.createOrder(orderReq);
        return "An order has been created and placed.";
    }
}
