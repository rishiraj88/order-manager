package om.order.dto;

import java.math.BigDecimal;

public record OrderReq(String id, String orderNumber, String itemSkuCode,
                       BigDecimal pricePerItem,Integer quantity,UserDetails userDetails) {
}
