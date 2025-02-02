package om.notification.service;

public interface INotificationService {
    public void listen(om.notification.service.OrderPlacedEvent orderPlacedEvent);
}
