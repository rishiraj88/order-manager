package om.product.dto;

import java.math.BigDecimal;

public record ProductReq(String id,String name, String desc, String skuCode, BigDecimal pricePerItemUnit) {
}
