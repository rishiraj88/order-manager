package om.order.svc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.order.clients.InventoryClient;
import om.order.config.Constants;
import om.order.dao.OrderRepo;
import om.order.dto.OrderReq;
import om.order.entity.Order;
import om.order.event.OrderPlacedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Transactional
@Service @RequiredArgsConstructor @Slf4j
public class OrderServiceImpl implements IOrderService {
    private final OrderRepo  orderRepo;

    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    @Override
    public void createOrder(OrderReq orderReq) {
        if(inventoryClient.isInStock(orderReq.itemSkuCode(),orderReq.quantity())) {
            log.debug("The requested items are available in inventory.");
            Order newOrder = Order.builder()
                    .id(UUID.randomUUID().toString())
                    .orderNumber(orderReq.orderNumber())
                    .itemSkuCode(orderReq.itemSkuCode())
                    .pricePerItem(orderReq.pricePerItem())
                    .quantity(orderReq.quantity())
                    .build();
            orderRepo.save(newOrder);
            // Send success message to message queue (with Kafka tooling)
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(newOrder.getOrderNumber(),orderReq.userDetails().emailAddress());
            log.info("Sending the details of new order {} to 'order-placed' queue...",orderPlacedEvent);
            kafkaTemplate.send(Constants.orderPlacedQueueName,orderPlacedEvent);
            log.info("Sent the details of new order {} to 'order-placed' queue.", orderPlacedEvent);

        } else {
            throw new InventoryShortOfStockException(orderReq.itemSkuCode(), orderReq.quantity());
        }
    }
}
