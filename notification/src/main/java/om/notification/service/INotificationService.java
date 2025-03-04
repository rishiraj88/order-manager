package om.notification.service;

import om.notification.dto.OrderPlacedEvent;

public interface INotificationService {
    public void listen(OrderPlacedEvent orderPlacedEvent);
}
