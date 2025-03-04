package om.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.notification.dto.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements INotificationService {
    @Override
    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        log.info("Got a message out of 'order-placed' topic {}", orderPlacedEvent);

        //send email to customer.

        log.info("Order placement notification sent by email."); // On success
        // log.info("Order problem notification sent by email."); // On failure

    }
}
