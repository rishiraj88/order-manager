package om.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.order.clients.InventoryClient;
import om.order.config.Constants;
import om.order.dao.OrderRepo;
import om.order.dto.OrderReq;
import om.order.dto.OrderResp;
import om.order.entity.Order;
import om.order.event.OrderPlacedEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Transactional
@Service @RequiredArgsConstructor @Slf4j
public class OrderServiceImpl implements IOrderService {
    private final OrderRepo  orderRepo;

    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    @Override
    public void createOrder(OrderReq orderReq) {
        if(inventoryClient.isItemInStock(orderReq.itemSkuCode(),orderReq.quantity())) {
            log.debug("The requested item quantity is available in inventory.");
            Order newOrder = Order.builder()
                    .id(UUID.randomUUID().toString())
                    .orderNumber(orderReq.orderNumber())
                    .itemSkuCode(orderReq.itemSkuCode())
                    .pricePerItem(orderReq.pricePerItem())
                    .quantity(orderReq.quantity())
                    .build();
            orderRepo.save(newOrder);

            // Send success message to message queue (with Kafka tooling)
            /* The following services among others may consume the message out of the queue for respective processes:
            :: Analytics service,
            :: Dashboard service,
            :: Fraud detection service,  and
            :: Invoicing & Taxation service.
            */
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(newOrder.getOrderNumber(),orderReq.userDetails().emailAddress());
            log.info("Placing the details of new order {} into the queue: 'order-placed'...",orderPlacedEvent);
            kafkaTemplate.send(Constants.orderPlacedQueueName,orderPlacedEvent);
            log.info("Placed the details of new order {} into the queue: 'order-placed'.", orderPlacedEvent);

        } else {
            throw new InventoryShortOfStockException(orderReq.itemSkuCode(), orderReq.quantity());
        }
    }

    @Override
    public List<OrderResp> getOrders(Pageable pageable){return new ArrayList<>();}

}
