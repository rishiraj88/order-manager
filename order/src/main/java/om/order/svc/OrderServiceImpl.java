package om.order.svc;

import lombok.RequiredArgsConstructor;
import om.order.clients.InventoryClient;
import om.order.dao.OrderRepo;
import om.order.dto.OrderReq;
import om.order.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Transactional
@Service @RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepo  orderRepo;
    private final InventoryClient inventoryClient;
    @Override
    public void createOrder(OrderReq orderReq) {
        if(inventoryClient.isInStock(orderReq.itemSkuCode(),orderReq.quantity())) {
            Order newOrder = Order.builder()
                    .id(UUID.randomUUID().toString())
                    .orderNumber(orderReq.orderNumber())
                    .itemSkuCode(orderReq.itemSkuCode())
                    .pricePerItem(orderReq.pricePerItem())
                    .quantity(orderReq.quantity())
                    .build();
            orderRepo.save(newOrder);
        } else {
            throw new InventoryShortOfStockException(orderReq.itemSkuCode(), orderReq.quantity());
        }
    }
}
