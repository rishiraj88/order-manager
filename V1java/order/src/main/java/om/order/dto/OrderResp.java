package om.order.dto;

import java.math.BigDecimal;

public record OrderResp(String id, String orderNumber, String itemSkuCode,                        BigDecimal pricePerItemUnit, Integer quantity, UserDetails userDetails) {}
