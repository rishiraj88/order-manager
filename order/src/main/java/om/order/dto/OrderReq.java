package om.order.dto;

import java.math.BigDecimal;

public record OrderReq(Long id, String orderNumber, String itemSkuCode,
                       BigDecimal pricePerItem,Integer quantity) {}
