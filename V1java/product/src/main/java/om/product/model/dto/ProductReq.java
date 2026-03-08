package om.product.model.dto;

import module java.base;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public record ProductReq(String id,String name, String desc, String skuCode, BigDecimal pricePerItemUnit) {
}
