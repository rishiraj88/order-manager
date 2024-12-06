package om.notif.svc;

public interface INotificationService {
    public void listen(OrderPlacedEvent orderPlacedEvent);
}
