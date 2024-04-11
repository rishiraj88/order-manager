package om.order.svc;

import lombok.RequiredArgsConstructor;
import om.order.dao.OrderRepo;
import om.order.dto.OrderReq;
import om.order.entity.Order;
import om.order.svc.IOrderService;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepo  orderRepo;
    @Override
    public void createOrder(OrderReq orderReq) {
        Order newOrder = Order.builder()
                .id(UUID.randomUUID().toString())
                .orderNumber(orderReq.orderNumber())
                .itemSkuCode(orderReq.itemSkuCode())
                .pricePerItem(orderReq.pricePerItem())
                .quantity(orderReq.quantity())
                .build();
orderRepo.save(newOrder);

    }
}
