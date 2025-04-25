package om.product.dto;

import java.math.BigDecimal;

public record ProductReq(String name, String desc, String skuCode, BigDecimal pricePerItem) {
}
