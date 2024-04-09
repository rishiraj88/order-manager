package om.product.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResp(String id,String name, String desc, BigDecimal pricePerItem) {
}
