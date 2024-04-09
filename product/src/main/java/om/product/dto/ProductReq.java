package om.product.dto;

import java.math.BigDecimal;

public record ProductReq(String name, String desc, BigDecimal pricePerItem) {
}
