package om.order.dto;

import java.math.BigDecimal;

public record OrderResp(Long id, String orderNumber, String itemSkuCode,
                        BigDecimal pricePerItem, Integer quantity, UserDetails userDetails) {
}
