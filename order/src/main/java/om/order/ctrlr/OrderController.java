package om.order.ctrlr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.order.config.Constants;
import om.order.dto.OrderReq;
import om.order.svc.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController @Slf4j
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final IOrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // modern method to specify Status Code
    public String createOrder(@RequestBody OrderReq orderReq) {
        orderService.createOrder(orderReq);
        log.debug(Constants.newOrderPlaced);
        return Constants.newOrderPlaced;
    }
}
