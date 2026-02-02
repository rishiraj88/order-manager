package om.product.exception;
import module java.base;

public record ErrorDetails(LocalDateTime timestamp, String errMsg, String errDesc, String errCode) {
}
