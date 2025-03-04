package om.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.notification.dto.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements INotificationService {
    private final JavaMailSender mailSender;

    @Override
    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        log.info("Got a message out of 'order-placed' topic {}", orderPlacedEvent);

        //send email to customer -- Start

        //Cook email message and MIME fields -- Start
        MimeMessagePreparator messageChef = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom("rrshop@emailde.de");
            helper.setTo(orderPlacedEvent.emailAddress().toString());
            helper.setSubject(String.format("SUCCESS: The order # %s has been placed.", orderPlacedEvent.orderNumber()));
            helper.setText(String.format("""
                    Dear %s,
                    
                    An order, identified as # %s has been placed recently. Kindly ensure to cite this order number for any queries and order tracking, whenever you do so.
                    
                    Thank you for shopping with us.
                    
                    With best greetings,
                    RR Shop
                    DE Branch
                    """
                    ,orderPlacedEvent.emailAddress().toString() // Smart method of placing comma. TypeScript/JavaScript coders should adopt this.
                    ,orderPlacedEvent.orderNumber()
            ));
        };
        //Cook email message and MIME fields -- Done

        try {
            mailSender.send(messageChef);
            log.info("SUCCESS: An email for new order placement is sent.");
        }catch (MailException e) {
            log.error("Something went wrong while sending the email notification for the order # {}.",orderPlacedEvent.orderNumber());
            throw new RuntimeException(String.format("Something went wrong while sending the email notification for the order # {}.",orderPlacedEvent.orderNumber()));
        }
        //send email to customer -- Done

        log.info("Order placement notification sent by email."); // On success
        // log.info("Order problem notification sent by email."); // On failure

    }
}
