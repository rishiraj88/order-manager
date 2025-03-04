package om.notification.dto;

public record OrderPlacedEvent(String orderNumber, String emailAddress) {}
