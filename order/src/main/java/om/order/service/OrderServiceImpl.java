package om.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.notification.dto.OrderPlacedEvent;
import om.order.clients.InventoryClient;
import om.order.config.Constants;
import om.order.dao.OrderRepo;
import om.order.dto.OrderReq;
import om.order.dto.OrderResp;
import om.order.dto.UserDetails;
import om.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Transactional
@Service @RequiredArgsConstructor @Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepo  orderRepo;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    @Override
    public void createOrder(OrderReq orderReq) {
        if(inventoryClient.isItemInStock(orderReq.itemSkuCode(),orderReq.quantity())) {
            log.debug("The requested item quantity is available in inventory.");
            var newOrder = Order.builder()
                    .id(UUID.randomUUID().toString())
                    .orderNumber(orderReq.orderNumber())
                    .itemSkuCode(orderReq.itemSkuCode())
                    .pricePerItem(orderReq.pricePerItem())
                    .quantity(orderReq.quantity())
                    .build();
            orderRepo.save(newOrder);

            // Send success message to message queue (with Kafka broker)
            /*
            The following services among others may consume the message out of the queue for processing for respective goals:
            :: Analytics service,
            :: Dashboard service,
            :: Fraud detection service,  and
            :: Invoicing & Taxation service.
            */
            var orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(newOrder.getOrderNumber());
            orderPlacedEvent.setEmailAddress(orderReq.userDetails().emailAddress());
            log.info("Placing the details of new order {} into the queue: 'order-placed'...",orderPlacedEvent);
            kafkaTemplate.send(Constants.ORDER_PLACED_QUEUE_NAME,orderPlacedEvent);
            log.info("Placed the details of new order {} into the queue: 'order-placed'.", orderPlacedEvent);

        } else {
            throw new InventoryShortOfStockException(orderReq.itemSkuCode(), orderReq.quantity());
        }
    }

    @Override
    public Page<OrderResp> getOrders(Pageable pageable){return orderRepo.findAll(pageable).map(order -> new OrderResp(order.getId(), order.getOrderNumber(),order.getItemSkuCode(),order.getPricePerItem(),order.getQuantity(),new UserDetails("emailAddress@domain.docom"," name")));}

}
