package om.product.exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String errMsg, String errDesc, String errCode) {
}
